package kakaopay.sec.service;

import java.util.List;

import javax.validation.Valid;

import kakaopay.sec.dto.GetUserListOutDto;
import kakaopay.sec.dto.PostUserInDto;
import kakaopay.sec.dto.PostUserOutDto;

public interface UserService {
	
	//사용자 추가
	public PostUserOutDto insertUser(@Valid PostUserInDto user);
	
	//전체 사용자 조회
	public List<GetUserListOutDto> selectAllUserList();

}
