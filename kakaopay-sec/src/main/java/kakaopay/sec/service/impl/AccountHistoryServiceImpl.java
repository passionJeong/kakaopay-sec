package kakaopay.sec.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import kakaopay.sec.dto.GetAccountHistoryListInDto;
import kakaopay.sec.dto.GetAccountHistoryListOutDto;
import kakaopay.sec.dto.GetAccountInfoOutDto;
import kakaopay.sec.dto.GetAllAccountHistoryListOutDto;
import kakaopay.sec.dto.PostAccountHistoryInDto;
import kakaopay.sec.dto.PostAccountHistoryOutDto;
import kakaopay.sec.exception.CustomException;
import kakaopay.sec.exception.ErrorCode;
import kakaopay.sec.mapper.AccountHistoryMapper;
import kakaopay.sec.service.AccountHistoryService;

/**
 * 계좌내역 서비스
 * @author jeongseoyeon
 * 계좌내역 등록, 조회
 */

@Service
public class AccountHistoryServiceImpl implements AccountHistoryService {
	
	private final AccountHistoryMapper accountHistoryMapper;
	
	public AccountHistoryServiceImpl(AccountHistoryMapper accountHistoryMapper) {
		this.accountHistoryMapper = accountHistoryMapper;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public PostAccountHistoryOutDto insertAccountHistory(@Valid PostAccountHistoryInDto history) {
		//실제 존재하는 계좌인지 우선 확인한다.
		GetAccountInfoOutDto accountInfo = accountHistoryMapper.selectAccountInfo(history.getAccountNum());
		
		if(ObjectUtils.isEmpty(accountInfo)) {
			throw new CustomException(ErrorCode.ACCT_NOT_FOUND);
		}
		
		//날짜가 입력되지 않았을 경우 현재 시간을 채워준다.
		if(ObjectUtils.isEmpty(history.getWithdrawlDate())) {
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			history.setWithdrawlDate(formatter.format(date));
		}
		
		int result = accountHistoryMapper.insertAccountHistory(history);
		
		PostAccountHistoryOutDto resultDto = new PostAccountHistoryOutDto();
		
		if(result == 1) {
			resultDto.setResult("계좌내역등록 성공");
		} else {
			throw new CustomException(ErrorCode.ACCT_HIST_JOIN_FAIL);
		}
		
		return resultDto;
	}

	@Transactional(readOnly = true)
	@Override
	public List<GetAllAccountHistoryListOutDto> selectAllAccountHistoryList() {
		List<GetAllAccountHistoryListOutDto> accountHistoryList = accountHistoryMapper.selectAllAccountHistoryList();
		
		//결과가 없을 경우
		if(CollectionUtils.isEmpty(accountHistoryList)) {
			throw new CustomException(ErrorCode.ACCT_HIST_NOT_FOUND);
		}
		
		return accountHistoryList;
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<GetAccountHistoryListOutDto> selectAccountHistoryList(GetAccountHistoryListInDto account) {
		//실제 존재하는 계좌인지 우선 확인한다.
		GetAccountInfoOutDto accountInfo = accountHistoryMapper.selectAccountInfo(account.getAccountNum());
		
		//존재하지 않는 계좌일경우
		if(ObjectUtils.isEmpty(accountInfo)) {
			throw new CustomException(ErrorCode.ACCT_NOT_FOUND);
		}
		
		List<GetAccountHistoryListOutDto> accountHistoryList = accountHistoryMapper.selectAccountHistoryList(account);
		
		if(CollectionUtils.isEmpty(accountHistoryList)) {
			throw new CustomException(ErrorCode.ACCT_HIST_NOT_FOUND);
		}
		
		return accountHistoryList;
	}

}
