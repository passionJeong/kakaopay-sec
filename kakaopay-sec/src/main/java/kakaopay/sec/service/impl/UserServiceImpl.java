package kakaopay.sec.service.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import kakaopay.sec.dto.GetUserListOutDto;
import kakaopay.sec.dto.PostUserInDto;
import kakaopay.sec.dto.PostUserOutDto;
import kakaopay.sec.exception.CustomException;
import kakaopay.sec.exception.ErrorCode;
import kakaopay.sec.mapper.UserMapper;
import kakaopay.sec.service.UserService;

/**
 * 사용자 서비스
 * @author jeongseoyeon
 * 사용자를 등록, 조회
 */

@Service
public class UserServiceImpl implements UserService {

	private final UserMapper userMapper;
	
	public UserServiceImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public PostUserOutDto insertUser(@Valid PostUserInDto user) {
		int result;
		
		//ID 중복
		try {
			result = userMapper.insertUser(user);
		} catch(DuplicateKeyException e) {
			throw new CustomException(ErrorCode.DUPLICATE_ID);
		}
		
		PostUserOutDto resultDto = new PostUserOutDto();
		
		if(result == 1) {
			resultDto.setResult("회원가입 성공");
		} else {
			throw new CustomException(ErrorCode.USER_JOIN_FAIL);
		}
		
		return resultDto;
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<GetUserListOutDto> selectAllUserList() {
		List<GetUserListOutDto> userList = userMapper.selectAllUserList();
		
		if(CollectionUtils.isEmpty(userList)) {
			throw new CustomException(ErrorCode.USER_NOT_FOUND);
		}
		
		return userList;
	}

}
