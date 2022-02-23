package kakaopay.sec.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kakaopay.sec.dto.GetWithdrawlYearSumInDto;
import kakaopay.sec.dto.GetWithdrawlYearSumOutDto;
import kakaopay.sec.exception.CustomException;
import kakaopay.sec.exception.ErrorCode;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WithdrawlYearSumServiceTest {
	
	@Autowired
	WithdrawlYearSumService withdrawlYearSumService;
	
	@DisplayName("년도를 입력받아, 해당년도의 예치금 총액을 출력")
	@Test
	@Order(value = 1)
	public void getWithdrawlYearSumServiceTest() {
		GetWithdrawlYearSumInDto input = new GetWithdrawlYearSumInDto();
		input.setYear("2020");
		GetWithdrawlYearSumOutDto output = withdrawlYearSumService.selectWithdrawlYearSum(input);
		
		assertEquals(37117500, output.getWithdrawlYearSum());
	}
	
	@DisplayName("년도 형식에 맞지않는 데이터를 입력받아, 해당년도의 예치금 총액을 출력 실패")
	@Test
	@Order(value = 2)
	public void getWithdrawlYearSumServiceExceptionTest() {
		GetWithdrawlYearSumInDto input = new GetWithdrawlYearSumInDto();
		input.setYear("22");
		
		//년도 형식이 잘못되었을 경우
		CustomException exception = assertThrows(CustomException.class, () -> {
			withdrawlYearSumService.selectWithdrawlYearSum(input);
        });
		
		assertEquals(ErrorCode.INVALID_YEAR, exception.getErrorCode());
	}
	
}
