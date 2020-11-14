package com.zhuanjingkj.zjcbe.business.service.auth;

import com.zhuanjingkj.zjcbe.business.contentext.AccountContextBO;
import com.zhuanjingkj.zjcbe.business.dto.account.requestDTO.LoginRequestDTO;
import com.zhuanjingkj.zjcbe.business.dto.account.responseDTO.AccountResponseDTO;
import com.zhuanjingkj.zjcbe.business.dto.account.responseDTO.LoginResponseDTO;
import com.zhuanjingkj.zjcbe.business.dto.power.PowerTreeDTO;
import com.zhuanjingkj.zjcbe.business.dto.power.RoleDTO;
import com.zhuanjingkj.zjcbe.commondata.baseDTO.ResultDTO;
import com.zhuanjingkj.zjcbe.commondata.baseDTO.SimpleResultDTO;
import com.zhuanjingkj.zjcbe.commondata.enums.CommonStatusEnum;
import com.zhuanjingkj.zjcbe.domain.entity.AccountAuthTokenEntity;
import com.zhuanjingkj.zjcbe.domain.entity.SysAuthEntity;
import com.zhuanjingkj.zjcbe.domain.entity.SysPowerEntity;
import com.zhuanjingkj.zjcbe.domain.entity.SysRoleEntity;
import com.zhuanjingkj.zjcbe.domain.po.AccountPO;
import com.zhuanjingkj.zjcbe.domain.po.AccountPowerPO;
import com.zhuanjingkj.zjcbe.utility.encrypt.DESUtil;
import com.zhuanjingkj.zjcbe.utility.encrypt.HashEncrypt;
import com.zhuanjingkj.zjcbe.utility.map.ObjectMapperUtils;
import com.zhuanjingkj.zjcbe.utility.output.CustomOutputUtility;
import com.zhuanjingkj.zjcbe.utility.string.StringUtils;
import com.zhuanjingkj.zjcbe.utility.time.DateTimeUtil;
import com.zhuanjingkj.zjcbe.utility.time.TimeStampUtil;
import com.zhuanjingkj.zjcbe.repository.account.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService implements IAuthService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	SysAuthRepository authRepository;

	@Autowired
	AccountPowerRepository accountPowerRepository;

	@Autowired
	SysPowerRepository sysPowerRepository;

	@Autowired
	AccountAuthTokenRepository authTokenRepository;

	@Autowired
	SysRoleRepository roleRepository;

	@Override
	public ResultDTO<LoginResponseDTO> login(LoginRequestDTO requestDTO) {
		SysAuthEntity sysAuthEntity = authRepository.getSysAuth(requestDTO.getAppId(), requestDTO.getSecretKey());
		if (sysAuthEntity == null) {
			return CustomOutputUtility.excuteFail("授权信息不存在,请与系统管理员联系");
		}
		AccountPO accountPO = accountRepository.getAccountInfoByCode(requestDTO.getUserCode());
		if (accountPO == null) {
			return CustomOutputUtility.excuteFail("用户名不存在");
		}
		if (accountPO.getStatus() != CommonStatusEnum.VALID.getValue()) {
			return CustomOutputUtility.excuteFail("用户状态无效");
		}
		String userPwd = HashEncrypt.GetMD5(requestDTO.getUserPassword());
		if (!accountPO.getUserPassword().equals(userPwd)) {
			return CustomOutputUtility.excuteFail("用户密码错误");
		}
		String accessToken = getAccessToken(accountPO.getAccountId(), requestDTO);
		if (StringUtils.isBlank(accessToken)) {
			return CustomOutputUtility.excuteFail("用户授权失败");
		}
		LoginResponseDTO responseDTO = new LoginResponseDTO();
		responseDTO.setAccessToken(accessToken);

		AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
		ObjectMapperUtils.map(accountPO, accountResponseDTO);
		List<AccountPowerPO> powerList = accountPowerRepository.getAccountPowerList(accountPO.getAccountId());
		accountResponseDTO.setPowerList(powerList);
		responseDTO.setAccountInfo(accountResponseDTO);

		return CustomOutputUtility.excuteSuccess(responseDTO);
	}

	/**
	 * 根据用户信息生成token
	 *
	 * @param accountId
	 * @param requestDTO
	 * @return
	 */
	private String getAccessToken(int accountId, LoginRequestDTO requestDTO) {
		try {
			String timeStamp = TimeStampUtil.dateToStamp(new Date());
			String key = requestDTO.getSecretKey();
			if (requestDTO.getSecretKey().length() > 8) {
				key = requestDTO.getSecretKey().substring(0, 8);
			}
			String data = String.format("%s_%s_%s_%s", requestDTO.getAppId(), accountId, requestDTO.getUserCode(), timeStamp);
			String token = HashEncrypt.GetMD5(DESUtil.encryptDES(data, key));
			if (!StringUtils.isBlank(token)) {
				AccountAuthTokenEntity tokenEntity = new AccountAuthTokenEntity();
				tokenEntity.setAccountId(accountId);
				tokenEntity.setToken(token);
				tokenEntity.setExpireTime(DateTimeUtil.addHours(8, new Date()));
				tokenEntity.setCreateTime(new Date());
				tokenEntity.setStatus(CommonStatusEnum.VALID.getValue());
				authTokenRepository.addAccountAuth(tokenEntity);
			}
			return token;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 获取用户token获取用户上下文信息
	 *
	 * @param token
	 * @return
	 */
	@Override
	public ResultDTO<AccountContextBO> getAuthContextByToken(String token) {

		AccountAuthTokenEntity authTokenEntity = authTokenRepository.getAccountAuthToken(token);
		if (authTokenEntity == null) {
			return CustomOutputUtility.excuteFail("用户token无效，请重新登陆！");
		}
		if (authTokenEntity.getExpireTime().compareTo(new Date()) <= 0) {
			return CustomOutputUtility.excuteFail(-1, "用户token过期，请重新登陆！");
		}
		AccountPO accountPO = accountRepository.getAccountInfoById(authTokenEntity.getAccountId());
		if (accountPO == null) {
			return CustomOutputUtility.excuteFail("获取登陆用户信息失败，请重新登陆！");
		}
		AccountContextBO contextBO = new AccountContextBO();
		contextBO.setAccountId(accountPO.getAccountId());
		contextBO.setUserName(accountPO.getUserName());
		contextBO.setRoleCode(accountPO.getRoleCode());
		return CustomOutputUtility.excuteSuccess(contextBO);
	}

	@Override
	public SimpleResultDTO checkToken(String token) {
		AccountAuthTokenEntity authTokenEntity = authTokenRepository.getAccountAuthToken(token);
		if (authTokenEntity == null) {
			return CustomOutputUtility.excuteFail("用户token无效，请重新登陆！");
		}
		if (authTokenEntity.getExpireTime().compareTo(new Date()) <= 0) {
			return CustomOutputUtility.excuteFail(-1, "用户token过期，请重新登陆！");
		}
		AccountPO accountPO = accountRepository.getAccountInfoById(authTokenEntity.getAccountId());
		if (accountPO == null) {
			return CustomOutputUtility.excuteFail("用户信息不存在，请重新登陆！");
		}
		return CustomOutputUtility.excuteSuccess();
	}

	@Override
	public List<PowerTreeDTO> getAllPowerTree() {
		List<SysPowerEntity> powerEntities = sysPowerRepository.getAllPower();
		List<PowerTreeDTO> powerTree = powerEntities.stream().map(p -> {
			PowerTreeDTO treeDTO = new PowerTreeDTO();
			ObjectMapperUtils.map(p, treeDTO);
			return treeDTO;
		}).collect(Collectors.toList());
		return buidTree(powerTree);
	}

	@Override
	public List<PowerTreeDTO> getAccountPowerTree(int accountId) {
		List<AccountPowerPO> accountPowerPOS = accountPowerRepository.getAccountPowerList(accountId);
		List<PowerTreeDTO> powerTree = accountPowerPOS.stream().map(p -> {
			PowerTreeDTO treeDTO = new PowerTreeDTO();
			ObjectMapperUtils.map(p, treeDTO);
			return treeDTO;
		}).collect(Collectors.toList());
		return buidTree(powerTree);
	}

	@Override
	public List<RoleDTO> getRoleList() {
		List<SysRoleEntity> entities = roleRepository.getRoleList();
		return entities.stream().map(e -> {
			RoleDTO roleDTO = new RoleDTO();
			ObjectMapperUtils.map(e, roleDTO);
			return roleDTO;
		}).collect(Collectors.toList());
	}

	static List<PowerTreeDTO> buidTree(List<PowerTreeDTO> list) {
		List<PowerTreeDTO> tree = new ArrayList<>();
		for (PowerTreeDTO node : list) {
			if (node.getParentId() == 0) {
				tree.add(findChild(node, list));
			}
		}
		return tree;
	}

	static PowerTreeDTO findChild(PowerTreeDTO node, List<PowerTreeDTO> list) {
		for (PowerTreeDTO n : list) {
			if (n.getParentId() == node.getPowerId()) {
				if (node.getChilds() == null) {
					node.setChilds(new ArrayList<PowerTreeDTO>());
				}
				node.getChilds().add(findChild(n, list));
			}
		}
		return node;
	}

}
