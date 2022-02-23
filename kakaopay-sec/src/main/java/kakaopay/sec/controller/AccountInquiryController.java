package kakaopay.sec.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kakaopay.sec.dto.GetAccountBalanceInDto;
import kakaopay.sec.dto.GetAccountBalanceOutDto;
import kakaopay.sec.dto.GetWithdrawlYearSumInDto;
import kakaopay.sec.dto.GetWithdrawlYearSumOutDto;
import kakaopay.sec.service.AccountBalanceService;
import kakaopay.sec.service.WithdrawlYearSumService;

/**
 * 계좌 정보 서비스 컨트롤러
 * @author jeongseoyeon
 * 특정 조건을 기반으로 계좌의 정보를 추출할 때 사용한다.
 */

@RestController
public class AccountInquiryController {
	
	private final AccountBalanceService accountBanlanceService;
	
	private final WithdrawlYearSumService withdrawlYearSumService;
	
	public AccountInquiryController(AccountBalanceService accountBanlanceService, WithdrawlYearSumService withdrawlYearSumService) {
		this.accountBanlanceService = accountBanlanceService;
		this.withdrawlYearSumService = withdrawlYearSumService;
	}
	
	@ApiOperation(value = "계좌별 예치금", notes = "사용자를 입력받아, 사용자의 계좌별 예치금을 출력")
	@ResponseBody
	@GetMapping("/v1/account-balance/{userId}")
	public List<GetAccountBalanceOutDto> getAccountBalance(@ApiParam(value = "사용자 ID", example = "1", required = true) @PathVariable("userId") String userId) {
		GetAccountBalanceInDto input = new GetAccountBalanceInDto();
		input.setUserId(userId);
		List<GetAccountBalanceOutDto> output = accountBanlanceService.selectAccountBalance(input);
		
		return output;
	}
	
	@ApiOperation(value = "년도 기준 예치금 총액", notes = "년도를 입력받아, 해당년도의 예치금 총액을 출력")
	@ResponseBody
	@GetMapping("/v1/withdrawl-year-sum/{year}")
	public GetWithdrawlYearSumOutDto getWithdrawlYearSum(@ApiParam(value = "년도", example = "2021", required = true) @PathVariable("year") String year) {
		GetWithdrawlYearSumInDto input = new GetWithdrawlYearSumInDto();
		input.setYear(year);
		GetWithdrawlYearSumOutDto output = withdrawlYearSumService.selectWithdrawlYearSum(input);
		
		return output;
	}
}
