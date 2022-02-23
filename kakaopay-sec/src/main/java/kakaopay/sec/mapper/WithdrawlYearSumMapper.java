package kakaopay.sec.mapper;

import org.apache.ibatis.annotations.Mapper;

import kakaopay.sec.dto.GetWithdrawlYearSumInDto;
import kakaopay.sec.dto.GetWithdrawlYearSumOutDto;

@Mapper
public interface WithdrawlYearSumMapper {
	
	GetWithdrawlYearSumOutDto selectWithdrawlYearSum(GetWithdrawlYearSumInDto input);

}