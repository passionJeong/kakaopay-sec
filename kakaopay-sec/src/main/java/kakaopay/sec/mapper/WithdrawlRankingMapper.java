package kakaopay.sec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kakaopay.sec.dto.GetWithdrawlRankingInDto;
import kakaopay.sec.dto.GetWithdrawlRankingOutDto;

@Mapper
public interface WithdrawlRankingMapper {
	
	List<GetWithdrawlRankingOutDto> selectWithdrawlRanking(GetWithdrawlRankingInDto input);

}