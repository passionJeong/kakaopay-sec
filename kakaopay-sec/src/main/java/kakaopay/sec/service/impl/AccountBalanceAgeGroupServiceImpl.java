package kakaopay.sec.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import kakaopay.sec.dto.GetAccountBalanceAgeGroupOutDto;
import kakaopay.sec.exception.CustomException;
import kakaopay.sec.exception.ErrorCode;
import kakaopay.sec.mapper.AccountBalanceAgeGroupMapper;
import kakaopay.sec.service.AccountBalanceAgeGroupService;

/**
 * 연령대별 예치금 서비스
 * @author jeongseoyeon
 * 사용자 나이대 별로, 평균 예치금을 출력
 */

@Service
public class AccountBalanceAgeGroupServiceImpl implements AccountBalanceAgeGroupService {
	
	private final AccountBalanceAgeGroupMapper accountBalanceAgeGroupMapper;
	
	public AccountBalanceAgeGroupServiceImpl(AccountBalanceAgeGroupMapper accountBalanceAgeGroupMapper) {
		this.accountBalanceAgeGroupMapper = accountBalanceAgeGroupMapper;
	}

	@Transactional(readOnly = true)
	@Override
	public List<GetAccountBalanceAgeGroupOutDto> selectAccountBalanceAgeGroup() {
		List<GetAccountBalanceAgeGroupOutDto> accountBalanceAgeGroup = accountBalanceAgeGroupMapper.selectAccountBalanceAgeGroup();
		
		//결과가 없을 경우
		if(CollectionUtils.isEmpty(accountBalanceAgeGroup)) {
			throw new CustomException(ErrorCode.ACCT_HIST_NOT_FOUND);
		}
		
		return accountBalanceAgeGroup;
	}
	
}
