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
import org.springframework.dao.DataIntegrityViolationException;

import kakaopay.sec.dto.GetAccountHistoryListInDto;
import kakaopay.sec.dto.GetAccountHistoryListOutDto;
import kakaopay.sec.dto.GetAllAccountHistoryListOutDto;
import kakaopay.sec.dto.PostAccountHistoryInDto;
import kakaopay.sec.dto.PostAccountHistoryOutDto;
import kakaopay.sec.exception.CustomException;
import kakaopay.sec.exception.ErrorCode;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountHistoryServiceTest {
	
	@Autowired
	AccountHistoryService accountHistoryService;
	
	@DisplayName("존재하지 않는 계좌번호의 계좌내역을 적재")
	@Test
	@Order(value = 1)
	public void insertAccountHistoryServiceTest() {
		PostAccountHistoryInDto input = new PostAccountHistoryInDto();
		input.setAccountNum("1000-24");
		input.setWithdrawlYn("Y");
		input.setWithdrawlAmount(1234567l);
		input.setWithdrawlDate("2022-02-22");
		
		PostAccountHistoryOutDto output = accountHistoryService.insertAccountHistory(input);
		
		assertEquals("계좌내역등록 성공", output.getResult());
	}
	
	@DisplayName("존재하지 않는 계좌번호의 계좌내역을 적재 실패")
	@Test
	@Order(value = 2)
	public void insertAccountHistoryServiceExceptionTest() {
		PostAccountHistoryInDto input = new PostAccountHistoryInDto();
		input.setAccountNum("1111-112");
		input.setWithdrawlYn("Y");
		input.setWithdrawlAmount(1234567l);
		input.setWithdrawlDate("2022-02-22");
		
		//등록되어있는 계좌가 아닐 경우
		CustomException exception = assertThrows(CustomException.class, () -> {
			accountHistoryService.insertAccountHistory(input);
        });
		
		assertEquals(ErrorCode.ACCT_NOT_FOUND, exception.getErrorCode());
		
		input.setAccountNum("1000-24");
		input.setWithdrawlYn("Y");
		input.setWithdrawlAmount(1234567l);
		input.setWithdrawlDate("202-0222");
		
		//날짜 포맷에 맞지 않을 경우
		assertThrows(DataIntegrityViolationException.class, () -> {
			accountHistoryService.insertAccountHistory(input);
        });
	}
	
	@DisplayName("전체 계좌내역을 조회")
	@Test
	@Order(value = 3)
	public void selectAllAccountHistoryListServiceTest() {
		List<GetAllAccountHistoryListOutDto> output = accountHistoryService.selectAllAccountHistoryList();
		
		assertNotEquals(0, output.size());
	}
	
	@DisplayName("특정 계좌번호의 계좌내역을 조회")
	@Test
	@Order(value = 4)
	public void selectAccountHistoryListServiceTest() {
		GetAccountHistoryListInDto input = new GetAccountHistoryListInDto();
		input.setAccountNum("1000-01");
		List<GetAccountHistoryListOutDto> output = accountHistoryService.selectAccountHistoryList(input);
		
		assertNotEquals(0, output.size());
	}
	
	@DisplayName("존재하지 않는 계좌번호의 계좌내역을 조회 실패")
	@Test
	@Order(value = 5)
	public void selectAccountHistoryListServiceExceptionTest() {
		GetAccountHistoryListInDto input = new GetAccountHistoryListInDto();
		input.setAccountNum("1323-11");
		
		CustomException exception = assertThrows(CustomException.class, () -> {
			accountHistoryService.selectAccountHistoryList(input);
        });
		
		assertEquals(ErrorCode.ACCT_NOT_FOUND, exception.getErrorCode());
	}
	
}
