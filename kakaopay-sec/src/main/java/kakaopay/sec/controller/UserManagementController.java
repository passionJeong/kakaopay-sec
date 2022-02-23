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
import kakaopay.sec.dto.GetUserListOutDto;
import kakaopay.sec.dto.PostUserInDto;
import kakaopay.sec.dto.PostUserOutDto;
import kakaopay.sec.service.UserService;

/**
 * 사용자 관리 컨트롤러
 * @author jeongseoyeon
 * 사용자 관리 관련 서비스를 처리한다.
 */

@RestController
public class UserManagementController {
	
	private final UserService userService;
	
	public UserManagementController(UserService userService) {
		this.userService = userService;
	}
	
	@ApiOperation(value = "사용자 추가", notes = "사용자를 추가합니다.")
	@ResponseBody
	@PostMapping("/v1/user")
	public PostUserOutDto postUser(@RequestBody @Valid @ApiParam(value = "신규 사용자 정보", required = true) PostUserInDto user) {
		PostUserOutDto output = userService.insertUser(user);
		
		return output;
	}
	
	@ApiOperation(value = "전체 사용자 리스트 조회", notes = "등록되어있는 사용자를 조회합니다.")
	@ResponseBody
	@GetMapping("/v1/all-user-list")
	public List<GetUserListOutDto> getAllUserList() {
		List<GetUserListOutDto> output = userService.selectAllUserList();
		
		return output;
	}
}
