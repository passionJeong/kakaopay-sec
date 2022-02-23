package kakaopay.sec.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kakaopay.sec.dto.GetAccountBalanceInDto;
import kakaopay.sec.dto.GetAccountBalanceOutDto;
import kakaopay.sec.exception.CustomException;
import kakaopay.sec.exception.ErrorCode;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountBalanceServiceTest {
	
	@Autowired
	AccountBalanceService accountBalanceService;
	
	@DisplayName("특정 사용자의 계좌 잔고를 조회")
	@Test
	@Order(value = 1)
	public void selectAccountBalanceServiceTest() {
		GetAccountBalanceInDto input = new GetAccountBalanceInDto();
		input.setUserId("17");
		List<GetAccountBalanceOutDto> output = accountBalanceService.selectAccountBalance(input);
		
		assertEquals("1000-15", output.get(0).getAccountNum());
		assertEquals(4022200, output.get(0).getAccountBalance());
	}
	
	@DisplayName("존재하지 않는 사용자의 계좌 잔고를 조회 실패")
	@Test
	@Order(value = 2)
	public void selectAccountBalanceServiceExceptionTest() {
		GetAccountBalanceInDto input = new GetAccountBalanceInDto();
		//존재하지 않는 사용자
		input.setUserId("dummyID");
		
		CustomException exception = assertThrows(CustomException.class, () -> {
			accountBalanceService.selectAccountBalance(input);
        });
		
		assertEquals(ErrorCode.USER_NOT_FOUND, exception.getErrorCode());
	}
}
