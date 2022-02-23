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

import kakaopay.sec.dto.GetAllAccountListOutDto;
import kakaopay.sec.dto.PostAccountInDto;
import kakaopay.sec.dto.PostAccountOutDto;
import kakaopay.sec.exception.CustomException;
import kakaopay.sec.exception.ErrorCode;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountServiceTest {
	
	@Autowired
	AccountService accountService;
	
	@DisplayName("계좌 등록")
	@Test
	@Order(value = 1)
	public void insertAccountServiceTest() {
		PostAccountInDto input = new PostAccountInDto();
		input.setUserId("1");
		input.setAccountNum("1234-01");
		
		PostAccountOutDto output = accountService.insertAccount(input);
		
		assertEquals("계좌등록 성공", output.getResult());
	}
	
	@DisplayName("존재하지 않는 사용자의 계좌, 기존재하는 계좌 등록 실패")
	@Test
	@Order(value = 2)
	public void insertAccountServiceExceptionTest() {
		PostAccountInDto input = new PostAccountInDto();
		input.setUserId("notExistUser");
		input.setAccountNum("1234-01");
		
		//존재하지 않는 사용자
		CustomException exception = assertThrows(CustomException.class, () -> {
			accountService.insertAccount(input);
        });
		
		assertEquals(ErrorCode.USER_NOT_FOUND, exception.getErrorCode());
		
		input.setUserId("1");
		input.setAccountNum("1234-01");
		
		//기존재하는 계좌
		exception = assertThrows(CustomException.class, () -> {
			accountService.insertAccount(input);
        });
		
		assertEquals(ErrorCode.DUPLICATE_ACCT, exception.getErrorCode());
	}
	
	@DisplayName("등록되어있는 계좌 조회")
	@Test
	@Order(value = 3)
	public void selectAllAccountListServiceTest() {
		List<GetAllAccountListOutDto> output = accountService.selectAllAccountList();
		
		assertNotEquals(0, output.size());
	}
	
}
