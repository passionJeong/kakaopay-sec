package kakaopay.sec.service;

import kakaopay.sec.dto.GetWithdrawlYearSumInDto;
import kakaopay.sec.dto.GetWithdrawlYearSumOutDto;

public interface WithdrawlYearSumService {
	
	public GetWithdrawlYearSumOutDto selectWithdrawlYearSum(GetWithdrawlYearSumInDto input);
	
}