package kakaopay.sec.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kakaopay.sec.dto.GetUserListOutDto;
import kakaopay.sec.dto.PostUserInDto;
import kakaopay.sec.dto.PostUserOutDto;
import kakaopay.sec.exception.CustomException;
import kakaopay.sec.exception.ErrorCode;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {
	
	@Autowired
	UserService userService;
	
	@DisplayName("사용자 등록")
	@Test
	@Order(value = 1)
	public void postUserServiceTest() {
		PostUserInDto input = new PostUserInDto();
		input.setUserId("junitTestID");
		input.setUserAge(32);
		input.setUserName("junitTESTname");
		
		PostUserOutDto output = userService.insertUser(input);
		
		assertEquals("회원가입 성공", output.getResult());
	}
	
	@DisplayName("기등록되어있는 ID 사용자 등록 실패")
	@Test
	@Order(value = 2)
	public void postUserServiceExceptionTest() {
		PostUserInDto input = new PostUserInDto();
		input.setUserId("junitTestID");
		input.setUserAge(32);
		input.setUserName("junitTESTname");
		
		//중복된 ID
		CustomException exception = assertThrows(CustomException.class, () -> {
			userService.insertUser(input);
        });
		
		assertEquals(ErrorCode.DUPLICATE_ID, exception.getErrorCode());
	}
	
	@DisplayName("등록되어있는 사용자 전체 조회")
	@Test
	@Order(value = 3)
	public void getUserListServiceTest() {
		List<GetUserListOutDto> output = userService.selectAllUserList();
		
		assertNotEquals(0, output.size());
	}
	
}
