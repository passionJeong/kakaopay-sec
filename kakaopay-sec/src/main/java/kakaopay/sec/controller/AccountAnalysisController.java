package kakaopay.sec.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kakaopay.sec.dto.GetAccountBalanceAgeGroupOutDto;
import kakaopay.sec.dto.GetWithdrawlRankingInDto;
import kakaopay.sec.dto.GetWithdrawlRankingOutDto;
import kakaopay.sec.service.AccountBalanceAgeGroupService;
import kakaopay.sec.service.WithdrawlRankingService;

/**
 * 계좌 관련 통계 서비스 컨트롤러
 * @author jeongseoyeon
 * 특정 조건을 기반으로 통계를 산출할 때 사용한다.
 */

@RestController
public class AccountAnalysisController {
	
	private final AccountBalanceAgeGroupService accountBalanceAgeGroupService;
	
	private final WithdrawlRankingService withdrawlRankingService;
	
	public AccountAnalysisController(AccountBalanceAgeGroupService acountBalanceAgeGroupService, WithdrawlRankingService withdrawlRankingService) {
		this.accountBalanceAgeGroupService = acountBalanceAgeGroupService;
		this.withdrawlRankingService = withdrawlRankingService;
	}
	
	@ApiOperation(value = "나이대별 평균 예치금", notes = "사용자 나이대 별로, 평균 예치금을 출력")
	@ResponseBody
	@GetMapping("/v1/account-balance-age-group")
	public List<GetAccountBalanceAgeGroupOutDto> getAccountBalanceAgeGroup() {
		List<GetAccountBalanceAgeGroupOutDto> output = accountBalanceAgeGroupService.selectAccountBalanceAgeGroup();
		
		return output;
	}
	
	@ApiOperation(value = "사용자별 예치금 랭킹", notes = "기간을 입력받아, 돈을 많이 예치한 사용자 순으로 정렬해서 출력")
	@ResponseBody
	@GetMapping("/v1/withdrawl-ranking/{startDate}/{endDate}")
	public List<GetWithdrawlRankingOutDto> getWithdrawlRanking(@PathVariable("startDate") @ApiParam(value = "시작일", example = "2021-01-01", required = true) String startDate,
			@PathVariable("endDate") @ApiParam(value = "종료일", example = "2022-01-01", required = true) String endDate) {
		GetWithdrawlRankingInDto input = new GetWithdrawlRankingInDto();
		input.setStartDate(startDate);
		input.setEndDate(endDate);
		
		List<GetWithdrawlRankingOutDto> output = withdrawlRankingService.selectWithdrawlRanking(input);
		
		return output;
	}
	
}
