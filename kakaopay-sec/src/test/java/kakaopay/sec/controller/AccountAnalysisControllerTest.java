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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;

import kakaopay.sec.dto.GetAccountBalanceAgeGroupOutDto;
import kakaopay.sec.dto.GetWithdrawlRankingInDto;
import kakaopay.sec.dto.GetWithdrawlRankingOutDto;
import kakaopay.sec.service.AccountBalanceAgeGroupService;
import kakaopay.sec.service.WithdrawlRankingService;

@WebMvcTest(AccountAnalysisController.class)
public class AccountAnalysisControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AccountBalanceAgeGroupService accountBalanceAgeGroupService;

	@MockBean
	private WithdrawlRankingService withdrawlRankingService;
	
	@DisplayName("나이대별 평균 예치금 contoroller 테스트")
	@Test
	public void getAccountBalanceAgeGroupTest() throws Exception {
		JSONArray mockOutputList = new JSONArray();
		JSONObject mockOutput = new JSONObject();
		
		mockOutput.put("ageGroup", 20);
		mockOutput.put("accountBalance", 123456);
		mockOutputList.put(mockOutput);

		mockOutput = new JSONObject();
		mockOutput.put("ageGroup", 30);
		mockOutput.put("accountBalance", 654321);
		mockOutputList.put(mockOutput);
		
		ObjectMapper mapper = new ObjectMapper();
		List<GetAccountBalanceAgeGroupOutDto> outputDto = Arrays.asList(mapper.readValue(mockOutputList.toString(), GetAccountBalanceAgeGroupOutDto[].class));
		
		given(accountBalanceAgeGroupService.selectAccountBalanceAgeGroup()).willReturn(outputDto);

		mockMvc.perform(get("/v1/account-balance-age-group"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
		
		verify(accountBalanceAgeGroupService, times(1)).selectAccountBalanceAgeGroup();
	}
	
	@DisplayName("사용자별 예치금 랭킹 controller 테스트")
	@Test
	public void getWithdrawlRankingTest() throws Exception {
		JSONArray mockOutputList = new JSONArray();
		JSONObject mockOutput = new JSONObject();
		
		mockOutput.put("userId", "ranking1");
		mockOutput.put("userName", "no.1-user");
		mockOutput.put("withdrawlAmount", 123456);
		mockOutputList.put(mockOutput);

		mockOutput = new JSONObject();
		mockOutput.put("userId", "ranking2");
		mockOutput.put("userName", "no.2-user");
		mockOutput.put("withdrawlAmount", 12345);
		mockOutputList.put(mockOutput);
		
		ObjectMapper mapper = new ObjectMapper();
		List<GetWithdrawlRankingOutDto> outputDto = Arrays.asList(mapper.readValue(mockOutputList.toString(), GetWithdrawlRankingOutDto[].class));
		
		given(withdrawlRankingService.selectWithdrawlRanking(isA(GetWithdrawlRankingInDto.class))).willReturn(outputDto);

		mockMvc.perform(get("/v1/withdrawl-ranking/2022-02-01/2022-02-28"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
		
		verify(withdrawlRankingService, times(1)).selectWithdrawlRanking(isA(GetWithdrawlRankingInDto.class));
	}
	
}
