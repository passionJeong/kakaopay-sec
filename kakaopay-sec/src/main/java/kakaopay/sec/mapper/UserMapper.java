package kakaopay.sec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kakaopay.sec.dto.GetUserListOutDto;
import kakaopay.sec.dto.PostUserInDto;

@Mapper
public interface UserMapper {
	
	//사용자 추가
	int insertUser(PostUserInDto user);
	
	//전체 사용자 조회
	List<GetUserListOutDto> selectAllUserList();

}
