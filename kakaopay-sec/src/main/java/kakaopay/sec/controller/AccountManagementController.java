package kakaopay.sec.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kakaopay.sec.dto.GetAllAccountListOutDto;
import kakaopay.sec.dto.PostAccountInDto;
import kakaopay.sec.dto.PostAccountOutDto;
import kakaopay.sec.service.AccountService;

/**
 * 계좌 관리 서비스 컨트롤러
 * @author jeongseoyeon
 * 계좌 관리를 위한 서비스를 처리한다.
 */
@RestController
public class AccountManagementController {
	
	private final AccountService accountService;
	
	public AccountManagementController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@ApiOperation(value = "계좌 추가", notes = "계좌를 추가합니다.")
	@ResponseBody
	@PostMapping("/v1/account")
	public PostAccountOutDto postAccount(@RequestBody @Valid @ApiParam(value = "신규 계좌 정보", required = true) PostAccountInDto account) {
		PostAccountOutDto output = accountService.insertAccount(account);
		
		return output;
	}
	
	@ApiOperation(value = "전체계좌 리스트 조회", notes = "등록되어있는 계좌를 조회합니다.")
	@ResponseBody
	@GetMapping("/v1/all-account-list")
	public List<GetAllAccountListOutDto> getAllAccountList() {
		List<GetAllAccountListOutDto> output = accountService.selectAllAccountList();
		
		return output;
	}
}
