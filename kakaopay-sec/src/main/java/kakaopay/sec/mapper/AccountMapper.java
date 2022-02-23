package kakaopay.sec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kakaopay.sec.dto.GetAllAccountListOutDto;
import kakaopay.sec.dto.GetUserInfoInDto;
import kakaopay.sec.dto.GetUserInfoOutDto;
import kakaopay.sec.dto.PostAccountInDto;

@Mapper
public interface AccountMapper {
	
	//사전 조회(사용자 정보)
	GetUserInfoOutDto selectUserInfo(GetUserInfoInDto input);
	
	//계좌 신규
	int insertAccount(PostAccountInDto account);
	
	List<GetAllAccountListOutDto> selectAllAccountList();

}
