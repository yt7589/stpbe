package com.zhuanjingkj.zjcbe.business.service.account;

import com.baomidou.mybatisplus.plugins.Page;
import com.zhuanjingkj.zjcbe.business.contentext.AccountContextBO;
import com.zhuanjingkj.zjcbe.business.contentext.AuthContextHolder;
import com.zhuanjingkj.zjcbe.business.dto.account.requestDTO.AccountRequestDTO;
import com.zhuanjingkj.zjcbe.business.dto.account.requestDTO.AccountSearchRequestDTO;
import com.zhuanjingkj.zjcbe.business.dto.account.requestDTO.PasswordModifyRequestDTO;
import com.zhuanjingkj.zjcbe.business.dto.account.requestDTO.RegisterRequestDTO;
import com.zhuanjingkj.zjcbe.business.dto.account.responseDTO.AccountResponseDTO;
import com.zhuanjingkj.zjcbe.commondata.baseDTO.ResultDTO;
import com.zhuanjingkj.zjcbe.commondata.baseDTO.SimpleResultDTO;
import com.zhuanjingkj.zjcbe.commondata.enums.CommonStatusEnum;
import com.zhuanjingkj.zjcbe.commondata.enums.RoleTypeEnum;
import com.zhuanjingkj.zjcbe.domain.entity.AccountEntity;
import com.zhuanjingkj.zjcbe.domain.entity.AccountPowerEntity;
import com.zhuanjingkj.zjcbe.domain.entity.SysAuthEntity;
import com.zhuanjingkj.zjcbe.domain.po.AccountPO;
import com.zhuanjingkj.zjcbe.domain.po.AccountPowerPO;
import com.zhuanjingkj.zjcbe.model.AccountSearchDTO;
import com.zhuanjingkj.zjcbe.repository.account.AccountPowerRepository;
import com.zhuanjingkj.zjcbe.repository.account.AccountRepository;
import com.zhuanjingkj.zjcbe.repository.account.SysAuthRepository;
import com.zhuanjingkj.zjcbe.utility.encrypt.HashEncrypt;
import com.zhuanjingkj.zjcbe.utility.map.ObjectMapperUtils;
import com.zhuanjingkj.zjcbe.utility.output.CustomOutputUtility;
import com.zhuanjingkj.zjcbe.utility.string.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService implements IAccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountPowerRepository powerRepository;

    @Autowired
    SysAuthRepository authRepository;

    @Override
    public Page<AccountResponseDTO> getAccountPageList(AccountSearchRequestDTO requestDTO) {
        AccountSearchDTO searchDTO = new AccountSearchDTO();
        searchDTO.setUserName(requestDTO.getUserName());
        searchDTO.setMobileCode(requestDTO.getMobileCode());

        AccountContextBO contextBO = AuthContextHolder.getAuthContext();
        if (contextBO.getRoleCode() == RoleTypeEnum.COMMON.getValue()) {
            searchDTO.setAccountId(contextBO.getAccountId());
        } else {
            searchDTO.setDeptIds(requestDTO.getDeptIds());
        }

        Page<AccountResponseDTO> page = new Page<>(requestDTO.getPageIndex(), requestDTO.getPageSize());
        int totalCount = accountRepository.getAccountCount(searchDTO);
        List<AccountPO> accountLst = accountRepository.getAccountPageList(searchDTO, requestDTO.getPageIndex(), requestDTO.getPageSize());

        if (accountLst != null && accountLst.size() > 0) {
            List<Integer> accountIds = accountLst.stream().map(a -> {
                return a.getAccountId();
            }).collect(Collectors.toList());
            List<AccountPowerPO> powerList = powerRepository.getAccountPowerList(accountIds);

            List<AccountResponseDTO> responseDTOS = accountLst.stream().map(po -> {
                AccountResponseDTO responseDTO = new AccountResponseDTO();
                ObjectMapperUtils.map(po, responseDTO);
                List<AccountPowerPO> currPowerLst = powerList.stream().filter(p -> p.getAccountId() == po.getAccountId()).collect(Collectors.toList());
                if (currPowerLst != null && currPowerLst.size() > 0) {
                    responseDTO.setPowerList(currPowerLst);
                }
                return responseDTO;
            }).collect(Collectors.toList());
            page.setTotal(totalCount);
            page.setRecords(responseDTOS);

        }
        return page;
    }

    @Override
    public ResultDTO<AccountResponseDTO> getAccountDetail(int accountId) {
        AccountPO accountPO = accountRepository.getAccountInfoById(accountId);
        if (accountPO == null) {
            return CustomOutputUtility.excuteFail("用户信息不存在");
        }
        AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
        ObjectMapperUtils.map(accountPO, accountResponseDTO);
        List<AccountPowerPO> powerList = powerRepository.getAccountPowerList(accountPO.getAccountId());
        accountResponseDTO.setPowerList(powerList);
        return CustomOutputUtility.excuteSuccess(accountResponseDTO);
    }

    @Override
    public SimpleResultDTO changePassword(PasswordModifyRequestDTO requestDTO) {

        AccountContextBO contextBO = AuthContextHolder.getAuthContext();
        AccountEntity accountEntity = accountRepository.getAccountEntityById(contextBO.getAccountId());
        if (accountEntity == null) {
            return CustomOutputUtility.excuteFail("你的帐户信息不存在");
        }
        if (!accountEntity.getUserPassword().equals(HashEncrypt.GetMD5(requestDTO.getOldPassword()))) {
            return CustomOutputUtility.excuteFail("原密码错误，请重新输入");
        }
        if (!requestDTO.getNewPassword().equals(requestDTO.getNewPasswordConfirm())) {
            return CustomOutputUtility.excuteFail("两次密码输入不一致，请重新输入");
        }
        accountEntity.setUserPassword(HashEncrypt.GetMD5(requestDTO.getNewPassword()));
        accountEntity.setUpdateTime(new Date());
        accountEntity.setUpdateBy(contextBO.getAccountId());
        accountRepository.updateAccount(accountEntity);
        return CustomOutputUtility.excuteSuccess();
    }

    @Override
    public SimpleResultDTO register(RegisterRequestDTO requestDTO) {
        SysAuthEntity sysAuthEntity = authRepository.getSysAuthById(requestDTO.getAppId());
        if (sysAuthEntity == null) {
            return CustomOutputUtility.excuteFail("授权信息不存在,请与系统管理员联系");
        }
        String sign = HashEncrypt.GetMD5(sysAuthEntity.getSecretKey() + requestDTO.getMobileCode());
        if (!requestDTO.getSign().equals(sign)) {
            return CustomOutputUtility.excuteFail("授权信息不存在,请与系统管理员联系");
        }
        AccountEntity accountEntity = new AccountEntity();
        ObjectMapperUtils.map(requestDTO, accountEntity);
        accountEntity.setUserPassword(HashEncrypt.GetMD5(requestDTO.getUserPassword()));
        accountEntity.setStatus(CommonStatusEnum.VALID.getValue());
        accountEntity.setRoleId(RoleTypeEnum.VISITOR.getRoleId());
        accountEntity.setCreateTime(new Date());
        if (accountRepository.existAccountByUserCode(requestDTO.getUserCode())) {
            return CustomOutputUtility.excuteFail("注册失败，该用户名已注册");
        }
        if (accountRepository.existAccountByMobileCode(requestDTO.getMobileCode())) {
            return CustomOutputUtility.excuteFail("注册失败，该手机号已注册");
        }
        accountRepository.insertAccount(accountEntity);
        return CustomOutputUtility.excuteSuccess();
    }

    @Override
    public SimpleResultDTO addAccount(AccountRequestDTO requestDTO) {
        AccountEntity accountEntity = new AccountEntity();
        ObjectMapperUtils.map(requestDTO, accountEntity);
        accountEntity.setUserPassword(HashEncrypt.GetMD5(requestDTO.getUserPassword()));
        accountEntity.setStatus(CommonStatusEnum.VALID.getValue());

        AccountContextBO contextBO = AuthContextHolder.getAuthContext();
        accountEntity.setCreateBy(contextBO.getAccountId());
        accountEntity.setCreateTime(new Date());

        if (accountRepository.existAccountByUserCode(requestDTO.getUserCode())) {
            return CustomOutputUtility.excuteFail(String.format("新增用户失败，用户名【%s】已存在", requestDTO.getUserCode()));
        }
        accountRepository.insertAccount(accountEntity);
        if (requestDTO.getPowerIds() != null && requestDTO.getPowerIds().size() > 0) {
            List<AccountPowerEntity> powerEntities = requestDTO.getPowerIds().stream().map(pid -> {
                AccountPowerEntity powerEntity = new AccountPowerEntity();
                powerEntity.setAccountId(accountEntity.getAccountId());
                powerEntity.setPowerId(pid);
                powerEntity.setCreateBy(contextBO.getAccountId());
                powerEntity.setCreateTime(new Date());
                return powerEntity;
            }).collect(Collectors.toList());
            powerRepository.batchAddPower(powerEntities);
        }
        return CustomOutputUtility.excuteSuccess();
    }

    @Override
    public SimpleResultDTO modifyAccount(AccountRequestDTO requestDTO) {
        AccountEntity accountEntity = accountRepository.getAccountEntityById(requestDTO.getAccountId());
        if (accountEntity == null) {
            return CustomOutputUtility.excuteFail("用户信息不存在");
        }
        if (!accountEntity.getUserCode().equals(requestDTO.getUserCode())) {
            if (accountRepository.existAccountByUserCode(requestDTO.getUserCode())) {
                return CustomOutputUtility.excuteFail(String.format("修改用户信息失败，用户名【%s】已存在", requestDTO.getUserCode()));
            }
        }
        if (accountEntity.getUserCode().toLowerCase().equals("admin")) {
            return CustomOutputUtility.excuteFail("admin为系统内置管理账号，请勿修改或删除！");
        }
        accountEntity.setUserCode(requestDTO.getUserCode());
        accountEntity.setMobileCode(requestDTO.getMobileCode());
        accountEntity.setRoleId(requestDTO.getRoleId());
        if (!StringUtils.isBlank(requestDTO.getUserPassword())) {
            accountEntity.setUserPassword(HashEncrypt.GetMD5(requestDTO.getUserPassword()));
        }
        AccountContextBO contextBO = AuthContextHolder.getAuthContext();
        accountEntity.setUpdateBy(contextBO.getAccountId());
        accountEntity.setUpdateTime(new Date());
        accountRepository.updateAccount(accountEntity);

        if (requestDTO.getPowerIds() != null && requestDTO.getPowerIds().size() > 0) {
            powerRepository.deleteByAccountId(accountEntity.getAccountId());
            List<AccountPowerEntity> powerEntities = requestDTO.getPowerIds().stream().map(pid -> {
                AccountPowerEntity powerEntity = new AccountPowerEntity();
                powerEntity.setAccountId(accountEntity.getAccountId());
                powerEntity.setPowerId(pid);
                powerEntity.setCreateBy(contextBO.getAccountId());
                powerEntity.setCreateTime(new Date());
                return powerEntity;
            }).collect(Collectors.toList());
            powerRepository.batchAddPower(powerEntities);
        }
        return CustomOutputUtility.excuteSuccess();
    }

    @Override
    public SimpleResultDTO deleteAccount(int accountId) {
        AccountEntity accountEntity = accountRepository.getAccountEntityById(accountId);
        if (accountEntity == null) {
            return CustomOutputUtility.excuteFail("账户信息不存在，无法删除");
        }
        if (accountEntity.getUserCode().toLowerCase().equals("admin")) {
            return CustomOutputUtility.excuteFail("admin为系统内置管理账号，请勿修改或删除！");
        }
        AccountContextBO contextBO = AuthContextHolder.getAuthContext();
        accountEntity.setUpdateBy(contextBO.getAccountId());
        accountEntity.setUpdateTime(new Date());
        accountEntity.setStatus(CommonStatusEnum.DELETE.getValue());
        boolean b1 = accountRepository.updateAccount(accountEntity);
        boolean b2 = powerRepository.deleteByAccountId(accountId);
        if (b1 && b2) {
            return CustomOutputUtility.excuteSuccess();
        } else {
            return CustomOutputUtility.excuteFail("删除用户信息失败");
        }
    }
}
