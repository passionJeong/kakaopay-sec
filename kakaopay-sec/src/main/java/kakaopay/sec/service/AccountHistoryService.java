package kakaopay.sec.service;

import java.util.List;

import javax.validation.Valid;

import kakaopay.sec.dto.GetAccountHistoryListInDto;
import kakaopay.sec.dto.GetAccountHistoryListOutDto;
import kakaopay.sec.dto.GetAllAccountHistoryListOutDto;
import kakaopay.sec.dto.PostAccountHistoryInDto;
import kakaopay.sec.dto.PostAccountHistoryOutDto;

public interface AccountHistoryService {
	
	public PostAccountHistoryOutDto insertAccountHistory(@Valid PostAccountHistoryInDto history);
	
	public List<GetAllAccountHistoryListOutDto> selectAllAccountHistoryList();
	
	public List<GetAccountHistoryListOutDto> selectAccountHistoryList(GetAccountHistoryListInDto account);

}