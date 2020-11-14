package com.zhuanjingkj.zjcbe.business.service.account;

import com.baomidou.mybatisplus.plugins.Page;
import com.zhuanjingkj.zjcbe.business.dto.account.requestDTO.AccountRequestDTO;
import com.zhuanjingkj.zjcbe.business.dto.account.requestDTO.AccountSearchRequestDTO;
import com.zhuanjingkj.zjcbe.business.dto.account.requestDTO.PasswordModifyRequestDTO;
import com.zhuanjingkj.zjcbe.business.dto.account.requestDTO.RegisterRequestDTO;
import com.zhuanjingkj.zjcbe.business.dto.account.responseDTO.AccountResponseDTO;
import com.zhuanjingkj.zjcbe.commondata.baseDTO.ResultDTO;
import com.zhuanjingkj.zjcbe.commondata.baseDTO.SimpleResultDTO;

public interface IAccountService {

    Page<AccountResponseDTO> getAccountPageList(AccountSearchRequestDTO requestDTO);

    SimpleResultDTO addAccount(AccountRequestDTO requestDTO);

    SimpleResultDTO deleteAccount(int accountId);

    SimpleResultDTO modifyAccount(AccountRequestDTO requestDTO);

    ResultDTO<AccountResponseDTO> getAccountDetail(int accountId);

    SimpleResultDTO changePassword(PasswordModifyRequestDTO requestDTO);

    SimpleResultDTO register(RegisterRequestDTO requestDTO);
}
