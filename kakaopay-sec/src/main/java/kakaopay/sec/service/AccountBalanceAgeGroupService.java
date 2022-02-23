package kakaopay.sec.service;

import java.util.List;

import kakaopay.sec.dto.GetAccountBalanceAgeGroupOutDto;

public interface AccountBalanceAgeGroupService {
	
	public List<GetAccountBalanceAgeGroupOutDto> selectAccountBalanceAgeGroup();
	
}