package kakaopay.sec.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kakaopay.sec.dto.GetAccountHistoryListInDto;
import kakaopay.sec.dto.GetAccountHistoryListOutDto;
import kakaopay.sec.dto.GetAllAccountHistoryListOutDto;
import kakaopay.sec.dto.PostAccountHistoryInDto;
import kakaopay.sec.dto.PostAccountHistoryOutDto;
import kakaopay.sec.service.AccountHistoryService;

/**
 * 계좌내역 추가 서비스 컨트롤러
 * @author jeongseoyeon
 * 계좌내역(거래내역) 관련 서비스를 처리한다.
 */

@RestController
public class AccountHistoryManagementController {
	
	private final AccountHistoryService accountHistoryService;
	
	public AccountHistoryManagementController(AccountHistoryService accountHistoryService) {
		this.accountHistoryService = accountHistoryService;
	}
	
	@ApiOperation(value = "계좌내역 추가", notes = "계좌내역을 추가합니다.")
	@ResponseBody
	@PostMapping("/v1/account-history")
	public PostAccountHistoryOutDto postAccountHistory(@RequestBody @Valid @ApiParam(value = "계좌 내역 정보", required = true) PostAccountHistoryInDto history) {
		PostAccountHistoryOutDto output = accountHistoryService.insertAccountHistory(history);
		
		return output;
	}
	
	@ApiOperation(value = "전체계좌내역 조회", notes = "등록되어있는 계좌내역을 조회합니다.")
	@ResponseBody
	@GetMapping("/v1/all-account-history-list")
	public List<GetAllAccountHistoryListOutDto> getAllAccountHistoryList() {
		List<GetAllAccountHistoryListOutDto> output = accountHistoryService.selectAllAccountHistoryList();
		
		return output;
	}
	
	@ApiOperation(value = "계좌내역 조회(계좌번호 입력)", notes = "특정 계좌에 등록되어있는 계좌내역을 조회합니다.")
	@ResponseBody
	@GetMapping("/v1/account-history-list/{accountNum}")
	public List<GetAccountHistoryListOutDto> getAccountHistoryList(@PathVariable("accountNum") @ApiParam(value = "계좌번호", example = "1000-01", required = true) String accountNum) {
		GetAccountHistoryListInDto account = new GetAccountHistoryListInDto();
		account.setAccountNum(accountNum);
		List<GetAccountHistoryListOutDto> output = accountHistoryService.selectAccountHistoryList(account);
		
		return output;
	}
}
