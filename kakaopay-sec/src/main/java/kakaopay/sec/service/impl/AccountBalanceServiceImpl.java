package kakaopay.sec.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import kakaopay.sec.dto.GetAccountBalanceInDto;
import kakaopay.sec.dto.GetAccountBalanceOutDto;
import kakaopay.sec.dto.GetUserInfoInDto;
import kakaopay.sec.dto.GetUserInfoOutDto;
import kakaopay.sec.exception.CustomException;
import kakaopay.sec.exception.ErrorCode;
import kakaopay.sec.mapper.AccountBalanceMapper;
import kakaopay.sec.service.AccountBalanceService;

/**
 * 계좌 잔고 서비스
 * @author jeongseoyeon
 * 사용자를 입력받아, 사용자의 계좌별 예치금을 출력
 */
@Service
public class AccountBalanceServiceImpl implements AccountBalanceService {
	
	private final AccountBalanceMapper accountBalanceMapper;
	
	public AccountBalanceServiceImpl(AccountBalanceMapper accountBalanceMapper) {
		this.accountBalanceMapper = accountBalanceMapper;
	}

	@Transactional(readOnly = true)
	@Override
	public List<GetAccountBalanceOutDto> selectAccountBalance(GetAccountBalanceInDto user) {
		GetUserInfoInDto userInfoInput = new GetUserInfoInDto();
		userInfoInput.setUserId(user.getUserId());
		GetUserInfoOutDto userInfo = accountBalanceMapper.selectUserInfo(userInfoInput);
		
		//존재하지 않는 사용자일경우
		if(ObjectUtils.isEmpty(userInfo)) {
			throw new CustomException(ErrorCode.USER_NOT_FOUND);
		}
		
		List<GetAccountBalanceOutDto> accountBalance = accountBalanceMapper.selectAccountBalance(user);
		
		//결과가 없을 경우
		if(CollectionUtils.isEmpty(accountBalance)) {
			throw new CustomException(ErrorCode.ACCT_HIST_NOT_FOUND);
		}
		
		return accountBalance;
	}

}
