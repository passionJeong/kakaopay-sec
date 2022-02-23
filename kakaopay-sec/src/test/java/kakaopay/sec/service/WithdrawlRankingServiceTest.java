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

import kakaopay.sec.dto.GetWithdrawlRankingInDto;
import kakaopay.sec.dto.GetWithdrawlRankingOutDto;
import kakaopay.sec.exception.CustomException;
import kakaopay.sec.exception.ErrorCode;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WithdrawlRankingServiceTest {
	
	@Autowired
	WithdrawlRankingService withdrawlRankingService;
	
	@DisplayName("기간을 입력받아, 돈을 많이 예치한 사용자 순으로 정렬해서 출력")
	@Test
	@Order(value = 1)
	public void getWithdrawlRankingServiceTest() {
		GetWithdrawlRankingInDto input = new GetWithdrawlRankingInDto();
		input.setStartDate("2020-01-01");
		input.setEndDate("2021-12-31");
		List<GetWithdrawlRankingOutDto> output = withdrawlRankingService.selectWithdrawlRanking(input);
		
		assertNotEquals(0, output.size());
	}
	
	@DisplayName("종료날짜가 시작날짜보다 앞서 돈을 많이 예치한 사용자 순으로 정렬해서 출력 실패")
	@Test
	@Order(value = 2)
	public void getWithdrawlRankingServiceExceptionTest() {
		GetWithdrawlRankingInDto input = new GetWithdrawlRankingInDto();
		input.setStartDate("2021-01-01");
		input.setEndDate("2020-12-31");
		
		//시작날짜가 종료날짜보다 클 때
		CustomException exception = assertThrows(CustomException.class, () -> {
			withdrawlRankingService.selectWithdrawlRanking(input);
        });
		
		assertEquals(ErrorCode.INVALID_DATE_RANGE, exception.getErrorCode());
	}
	
}
