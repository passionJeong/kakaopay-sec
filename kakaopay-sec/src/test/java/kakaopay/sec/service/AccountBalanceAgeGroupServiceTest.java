package kakaopay.sec.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kakaopay.sec.dto.GetAccountBalanceAgeGroupOutDto;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountBalanceAgeGroupServiceTest {
	
	@Autowired
	AccountBalanceAgeGroupService accountBalanceAgeGroupService;
	
	@DisplayName("나이대별 계좌잔고 조회")
	@Test
	@Order(value = 1)
	public void getWithdrawlYearSumServiceTest() {
		List<GetAccountBalanceAgeGroupOutDto> output = accountBalanceAgeGroupService.selectAccountBalanceAgeGroup();
		
		//연령대기 때문에, 10으로 나눈 나머지가 0이 되어야한다.
		assertEquals(0, output.get(0).getAgeGroup() % 10);
	}
	
}
