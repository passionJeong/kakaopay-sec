package kakaopay.sec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kakaopay.sec.dto.GetAccountBalanceAgeGroupOutDto;

@Mapper
public interface AccountBalanceAgeGroupMapper {
		
	List<GetAccountBalanceAgeGroupOutDto> selectAccountBalanceAgeGroup();

}