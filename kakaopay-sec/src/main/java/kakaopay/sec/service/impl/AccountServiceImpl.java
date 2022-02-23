package kakaopay.sec.service.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import kakaopay.sec.dto.GetAllAccountListOutDto;
import kakaopay.sec.dto.GetUserInfoInDto;
import kakaopay.sec.dto.GetUserInfoOutDto;
import kakaopay.sec.dto.PostAccountInDto;
import kakaopay.sec.dto.PostAccountOutDto;
import kakaopay.sec.exception.CustomException;
import kakaopay.sec.exception.ErrorCode;
import kakaopay.sec.mapper.AccountMapper;
import kakaopay.sec.service.AccountService;

/**
 * 계좌 서비스
 * @author jeongseoyeon
 * 계좌 등록, 조회
 */

@Service
public class AccountServiceImpl implements AccountService {
	
	private final AccountMapper accountMapper;
	
	public AccountServiceImpl(AccountMapper accountMapper) {
		this.accountMapper = accountMapper;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public PostAccountOutDto insertAccount(@Valid PostAccountInDto account) {
		int result;
		
		//실제 존재하는 사용자인지 우선 확인한다.
		GetUserInfoInDto user = new GetUserInfoInDto();
		user.setUserId(account.getUserId());
		GetUserInfoOutDto userInfo = accountMapper.selectUserInfo(user);
		
		//등록되어 있는 사용자가 아닌 경우
		if(ObjectUtils.isEmpty(userInfo)) {
			throw new CustomException(ErrorCode.USER_NOT_FOUND);
		}
		
		try {
			result = accountMapper.insertAccount(account);
		} catch(DuplicateKeyException e) {
			//등록되어 있는 계좌번호일 경우
			throw new CustomException(ErrorCode.DUPLICATE_ACCT);
		}
		
		PostAccountOutDto resultDto = new PostAccountOutDto();
		
		if(result == 1) {
			resultDto.setResult("계좌등록 성공");
		} else {
			throw new CustomException(ErrorCode.ACCT_JOIN_FAIL);
		}
		
		return resultDto;
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<GetAllAccountListOutDto> selectAllAccountList() {
		List<GetAllAccountListOutDto> accountList = accountMapper.selectAllAccountList();
		
		if(CollectionUtils.isEmpty(accountList)) {
			throw new CustomException(ErrorCode.ACCT_NOT_FOUND);
		}
		
		return accountList;
	}

}
