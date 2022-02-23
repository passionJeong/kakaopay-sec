package kakaopay.sec.controller;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;

import kakaopay.sec.dto.GetAccountBalanceInDto;
import kakaopay.sec.dto.GetAccountBalanceOutDto;
import kakaopay.sec.dto.GetWithdrawlYearSumInDto;
import kakaopay.sec.dto.GetWithdrawlYearSumOutDto;
import kakaopay.sec.service.AccountBalanceService;
import kakaopay.sec.service.WithdrawlYearSumService;

@WebMvcTest(AccountInquiryController.class)
public class AccountInquiryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AccountBalanceService accountBanlanceService;
	
	@MockBean
	private WithdrawlYearSumService withdrawlYearSumService;
	
	@DisplayName("계좌별 예치금 controller 테스트")
	@Test
	public void getAccountBalanceTest() throws Exception {
		JSONArray mockOutputList = new JSONArray();
		JSONObject mockOutput = new JSONObject();
		
		mockOutput.put("accountNum", "1234-01");
		mockOutput.put("accountBalance", 123456);
		mockOutputList.put(mockOutput);

		mockOutput = new JSONObject();
		mockOutput.put("accountNum", "1234-02");
		mockOutput.put("accountBalance", 654321);
		mockOutputList.put(mockOutput);
		
		ObjectMapper mapper = new ObjectMapper();
		List<GetAccountBalanceOutDto> outputDto = Arrays.asList(mapper.readValue(mockOutputList.toString(), GetAccountBalanceOutDto[].class));
		
		given(accountBanlanceService.selectAccountBalance(isA(GetAccountBalanceInDto.class))).willReturn(outputDto);

		mockMvc.perform(get("/v1/account-balance/mockID"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());;
		
		verify(accountBanlanceService, times(1)).selectAccountBalance(Mockito.any(GetAccountBalanceInDto.class));
	}
	
	@DisplayName("년도 기준 예치금 총액 controller 테스트")
	@Test
	public void getWithdrawlYearSumTest() throws Exception {
		JSONObject mockOutput = new JSONObject();
		
		mockOutput.put("withdrawlYearSum", 123456789);
		
		ObjectMapper mapper = new ObjectMapper();
		GetWithdrawlYearSumOutDto outputDto = mapper.readValue(mockOutput.toString(), GetWithdrawlYearSumOutDto.class);
		
		given(withdrawlYearSumService.selectWithdrawlYearSum(isA(GetWithdrawlYearSumInDto.class))).willReturn(outputDto);

		mockMvc.perform(get("/v1/withdrawl-year-sum/2011"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
		
		verify(withdrawlYearSumService, times(1)).selectWithdrawlYearSum(isA(GetWithdrawlYearSumInDto.class));
	}
	
}
