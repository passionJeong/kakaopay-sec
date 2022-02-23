package kakaopay.sec.service;

import java.util.List;

import kakaopay.sec.dto.GetAccountBalanceInDto;
import kakaopay.sec.dto.GetAccountBalanceOutDto;

public interface AccountBalanceService {
	
	public List<GetAccountBalanceOutDto> selectAccountBalance(GetAccountBalanceInDto input);
	
}