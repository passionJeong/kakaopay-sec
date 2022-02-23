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

import kakaopay.sec.dto.GetUserListOutDto;
import kakaopay.sec.dto.PostUserInDto;
import kakaopay.sec.dto.PostUserOutDto;
import kakaopay.sec.service.UserService;

@WebMvcTest(UserManagementController.class)
public class UserManagementControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;
	
	@DisplayName("사용자 추가 contoroller 테스트")
	@Test
	public void postUserTest() throws Exception {
		JSONObject mockInput = new JSONObject();
		mockInput.put("userId", "mockID1");
		mockInput.put("userName", "mock name");
		mockInput.put("userAge", 32);
		
		JSONObject mockOutput = new JSONObject();
		mockOutput.put("result", "회원가입 성공");
		
		ObjectMapper mapper = new ObjectMapper();
		PostUserOutDto outputDto = mapper.readValue(mockOutput.toString(), PostUserOutDto.class);
		
		given(userService.insertUser(isA(PostUserInDto.class))).willReturn(outputDto);

		mockMvc.perform(post("/v1/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mockInput.toString()))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());;
		
		verify(userService, times(1)).insertUser(isA(PostUserInDto.class));
	}
	
	@DisplayName("전체 사용자 리스트 조회 controller 테스트")
	@Test
	public void getAllUserListTest() throws Exception {
		JSONArray mockOutputList = new JSONArray();
		JSONObject mockOutput = new JSONObject();
		
		mockOutput.put("userId", "mockID1");
		mockOutput.put("userName", "mock name");
		mockOutput.put("userAge", 32);
		mockOutput.put("userJoinDate", "2011-01-01");
		mockOutputList.put(mockOutput);

		mockOutput = new JSONObject();
		mockOutput.put("userId", "mockID2");
		mockOutput.put("userName", "name mock");
		mockOutput.put("userAge", 23);
		mockOutput.put("userJoinDate", "2010-01-01");
		mockOutputList.put(mockOutput);
		
		ObjectMapper mapper = new ObjectMapper();
		List<GetUserListOutDto> outputDto = Arrays.asList(mapper.readValue(mockOutputList.toString(), GetUserListOutDto[].class));
		
		given(userService.selectAllUserList()).willReturn(outputDto);

		mockMvc.perform(get("/v1/all-user-list"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
		
		verify(userService, times(1)).selectAllUserList();
	}
	
}
