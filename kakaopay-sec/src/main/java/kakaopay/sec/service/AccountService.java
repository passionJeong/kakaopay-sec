package kakaopay.sec.service;

import java.util.List;

import javax.validation.Valid;

import kakaopay.sec.dto.GetAllAccountListOutDto;
import kakaopay.sec.dto.PostAccountInDto;
import kakaopay.sec.dto.PostAccountOutDto;

public interface AccountService {
	
	public PostAccountOutDto insertAccount(@Valid PostAccountInDto account);
	
	public List<GetAllAccountListOutDto> selectAllAccountList();
	
}