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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;

import kakaopay.sec.dto.GetAllAccountListOutDto;
import kakaopay.sec.dto.PostAccountInDto;
import kakaopay.sec.dto.PostAccountOutDto;
import kakaopay.sec.service.AccountService;

@WebMvcTest(AccountManagementController.class)
public class AccountManagementControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AccountService accountService;
	
	@DisplayName("계좌 추가 contoroller 테스트")
	@Test
	public void postAccountTest() throws Exception {
		JSONObject mockInput = new JSONObject();
		mockInput.put("userId", "mockID");
		mockInput.put("accountNum", "1100-13");
		
		JSONObject mockOutput = new JSONObject();
		mockOutput.put("result", "계좌등록 성공");
		
		ObjectMapper mapper = new ObjectMapper();
		PostAccountOutDto outputDto = mapper.readValue(mockOutput.toString(), PostAccountOutDto.class);
		
		given(accountService.insertAccount(isA(PostAccountInDto.class))).willReturn(outputDto);

		mockMvc.perform(post("/v1/account")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mockInput.toString()))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());;
		
		verify(accountService, times(1)).insertAccount(isA(PostAccountInDto.class));
	}
	
	@DisplayName("전체계좌 리스트 조회 controller 테스트")
	@Test
	public void getAllAccountListTest() throws Exception {
		JSONArray mockOutputList = new JSONArray();
		JSONObject mockOutput = new JSONObject();
		
		mockOutput.put("userId", "user1");
		mockOutput.put("accountNum", "1111-111");
		mockOutputList.put(mockOutput);

		mockOutput = new JSONObject();
		mockOutput.put("userId", "user2");
		mockOutput.put("accountNum", "1111-112");
		mockOutputList.put(mockOutput);
		
		ObjectMapper mapper = new ObjectMapper();
		List<GetAllAccountListOutDto> outputDto = Arrays.asList(mapper.readValue(mockOutputList.toString(), GetAllAccountListOutDto[].class));
		
		given(accountService.selectAllAccountList()).willReturn(outputDto);

		mockMvc.perform(get("/v1/all-account-list"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
		
		verify(accountService, times(1)).selectAllAccountList();
	}
	
}
