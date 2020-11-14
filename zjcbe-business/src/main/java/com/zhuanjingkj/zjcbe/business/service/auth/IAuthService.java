package com.zhuanjingkj.zjcbe.business.service.auth;

import com.zhuanjingkj.zjcbe.business.contentext.AccountContextBO;
import com.zhuanjingkj.zjcbe.business.dto.account.requestDTO.LoginRequestDTO;
import com.zhuanjingkj.zjcbe.business.dto.account.responseDTO.LoginResponseDTO;
import com.zhuanjingkj.zjcbe.business.dto.power.PowerTreeDTO;
import com.zhuanjingkj.zjcbe.business.dto.power.RoleDTO;
import com.zhuanjingkj.zjcbe.commondata.baseDTO.ResultDTO;
import com.zhuanjingkj.zjcbe.commondata.baseDTO.SimpleResultDTO;

import java.util.List;

public interface IAuthService {

	ResultDTO<LoginResponseDTO> login(LoginRequestDTO requestDTO);

	ResultDTO<AccountContextBO> getAuthContextByToken(String token);

	SimpleResultDTO checkToken(String token);

	List<PowerTreeDTO> getAllPowerTree();

	List<PowerTreeDTO> getAccountPowerTree(int accountId);

	List<RoleDTO> getRoleList();
}
