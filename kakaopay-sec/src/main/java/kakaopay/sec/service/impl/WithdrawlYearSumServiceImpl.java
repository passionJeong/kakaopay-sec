package kakaopay.sec.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import kakaopay.sec.dto.GetWithdrawlYearSumInDto;
import kakaopay.sec.dto.GetWithdrawlYearSumOutDto;
import kakaopay.sec.exception.CustomException;
import kakaopay.sec.exception.ErrorCode;
import kakaopay.sec.mapper.WithdrawlYearSumMapper;
import kakaopay.sec.service.WithdrawlYearSumService;

/**
 * 예치금 조회 서비스
 * @author jeongseoyeon
 * 년도를 입력받아, 해당년도의 예치금 총액을 출력한다.
 */

@Service
public class WithdrawlYearSumServiceImpl implements WithdrawlYearSumService {
	
	private final WithdrawlYearSumMapper withdrawlYearSumMapper;
	
	public WithdrawlYearSumServiceImpl(WithdrawlYearSumMapper withdrawlYearSumMapper) {
		this.withdrawlYearSumMapper = withdrawlYearSumMapper;
	}

	@Transactional(readOnly = true)
	@Override
	public GetWithdrawlYearSumOutDto selectWithdrawlYearSum(GetWithdrawlYearSumInDto input) {
		
		try {
			Integer.parseInt(input.getYear());
		} catch(NumberFormatException e) {
			throw new CustomException(ErrorCode.INVALID_YEAR);
		}
		
		//년도가 4자리가 아닐경우
		if(input.getYear().length() != 4) {
			throw new CustomException(ErrorCode.INVALID_YEAR);
		}
		
		GetWithdrawlYearSumOutDto accountBalanceYearSum = withdrawlYearSumMapper.selectWithdrawlYearSum(input);
		
		//결과가 없을 경우
		if(ObjectUtils.isEmpty(accountBalanceYearSum)) {
			throw new CustomException(ErrorCode.ACCT_HIST_NOT_FOUND);
		}
		
		return accountBalanceYearSum;
	}

}
