package kakaopay.sec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kakaopay.sec.dto.GetAccountBalanceInDto;
import kakaopay.sec.dto.GetAccountBalanceOutDto;
import kakaopay.sec.dto.GetUserInfoInDto;
import kakaopay.sec.dto.GetUserInfoOutDto;

@Mapper
public interface AccountBalanceMapper {
	
	GetUserInfoOutDto selectUserInfo(GetUserInfoInDto input);
	
	List<GetAccountBalanceOutDto> selectAccountBalance(GetAccountBalanceInDto user);

}