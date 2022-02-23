package kakaopay.sec.controller;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;

import kakaopay.sec.dto.GetAccountHistoryListInDto;
import kakaopay.sec.dto.GetAllAccountHistoryListOutDto;
import kakaopay.sec.dto.PostAccountHistoryInDto;
import kakaopay.sec.dto.PostAccountHistoryOutDto;
import kakaopay.sec.service.AccountHistoryService;

@WebMvcTest(AccountHistoryManagementController.class)
public class AccountHistoryManagementControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AccountHistoryService accountHistoryService;

	@DisplayName("계좌내역 추가 controller 테스트")
	@Test
	public void postAccountHistoryTest() throws Exception {
		JSONObject mockInput = new JSONObject();
		mockInput.put("accountNum", "1100-13");
		mockInput.put("withdrawlYn", "Y");
		mockInput.put("withdrawlAmount", 123456);
		mockInput.put("withdrawlDate", "2022-02-22");
		
		JSONObject mockOutput = new JSONObject();
		mockOutput.put("result", "success");
		
		ObjectMapper mapper = new ObjectMapper();
		PostAccountHistoryOutDto outputDto = mapper.readValue(mockOutput.toString(), PostAccountHistoryOutDto.class);
		
		given(accountHistoryService.insertAccountHistory(isA(PostAccountHistoryInDto.class))).willReturn(outputDto);

		mockMvc.perform(post("/v1/account-history")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mockInput.toString()))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
		
		verify(accountHistoryService, times(1)).insertAccountHistory(isA(PostAccountHistoryInDto.class));
	}
	
	@DisplayName("전체계좌내역 조회 controller 테스트")
	@Test
	public void getAllAccountHistoryListTest() throws Exception {
		JSONArray mockOutputList = new JSONArray();
		JSONObject mockOutput = new JSONObject();
		
		mockOutput.put("accountNum", "1111-11");
		mockOutput.put("withdrawlYn", "Y");
		mockOutput.put("withdrawlAmount", 123456);
		mockOutput.put("withdrawlDate", "2019-11-01");
		mockOutputList.put(mockOutput);

		mockOutput = new JSONObject();
		mockOutput.put("accountNum", "1111-12");
		mockOutput.put("withdrawlYn", "N");
		mockOutput.put("withdrawlAmount", 654321);
		mockOutput.put("withdrawlDate", "2020-01-01");
		mockOutputList.put(mockOutput);
		
		ObjectMapper mapper = new ObjectMapper();
		List<GetAllAccountHistoryListOutDto> outputDto = Arrays.asList(mapper.readValue(mockOutputList.toString(), GetAllAccountHistoryListOutDto[].class));
		
		given(accountHistoryService.selectAllAccountHistoryList()).willReturn(outputDto);

		mockMvc.perform(get("/v1/all-account-history-list"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
		
		verify(accountHistoryService, times(1)).selectAllAccountHistoryList();
	}
	
	@DisplayName("계좌내역 조회 controller 테스트")
	@Test
	public void getAccountHistoryListTest() throws Exception {
		JSONArray mockOutputList = new JSONArray();
		JSONObject mockOutput = new JSONObject();
		
		mockOutput.put("accountNum", "1111-11");
		mockOutput.put("withdrawlYn", "Y");
		mockOutput.put("withdrawlAmount", 123456);
		mockOutput.put("withdrawlDate", "2019-11-01");
		mockOutputList.put(mockOutput);

		mockOutput = new JSONObject();
		mockOutput.put("accountNum", "1111-11");
		mockOutput.put("withdrawlYn", "N");
		mockOutput.put("withdrawlAmount", 654321);
		mockOutput.put("withdrawlDate", "2020-01-01");
		mockOutputList.put(mockOutput);
		
		ObjectMapper mapper = new ObjectMapper();
		List<GetAllAccountHistoryListOutDto> outputDto = Arrays.asList(mapper.readValue(mockOutputList.toString(), GetAllAccountHistoryListOutDto[].class));
		
		given(accountHistoryService.selectAllAccountHistoryList()).willReturn(outputDto);

		mockMvc.perform(get("/v1/account-history-list/1111-11"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
		
		verify(accountHistoryService, times(1)).selectAccountHistoryList(Mockito.any(GetAccountHistoryListInDto.class));
	}
	
}
