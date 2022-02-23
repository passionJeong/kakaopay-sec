package kakaopay.sec.service;

import java.util.List;

import kakaopay.sec.dto.GetWithdrawlRankingInDto;
import kakaopay.sec.dto.GetWithdrawlRankingOutDto;

public interface WithdrawlRankingService {
	
	public List<GetWithdrawlRankingOutDto> selectWithdrawlRanking(GetWithdrawlRankingInDto input);
	
}