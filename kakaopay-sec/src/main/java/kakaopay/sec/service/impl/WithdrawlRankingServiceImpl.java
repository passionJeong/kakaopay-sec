package kakaopay.sec.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import kakaopay.sec.dto.GetWithdrawlRankingInDto;
import kakaopay.sec.dto.GetWithdrawlRankingOutDto;
import kakaopay.sec.exception.CustomException;
import kakaopay.sec.exception.ErrorCode;
import kakaopay.sec.mapper.WithdrawlRankingMapper;
import kakaopay.sec.service.WithdrawlRankingService;

/**
 * 예치금순 계좌내역 정렬 서비스
 * @author jeongseoyeon
 * 기간을 입력받아, 돈을 많이 예치한 사용자 순으로 정렬해서 출력한다.
 */

@Service
public class WithdrawlRankingServiceImpl implements WithdrawlRankingService {
	
	private final WithdrawlRankingMapper getWithdrawlRankingMapper;
	
	public WithdrawlRankingServiceImpl(WithdrawlRankingMapper getWithdrawlRankingMapper) {
		this.getWithdrawlRankingMapper = getWithdrawlRankingMapper;
	}

	@Transactional(readOnly = true)
	@Override
	public List<GetWithdrawlRankingOutDto> selectWithdrawlRanking(GetWithdrawlRankingInDto input) {
		String startDate = input.getStartDate().replace("-", "");
		String endDate = input.getEndDate().replace("-", "");
		
		//시작날짜가 종료날짜보다 큰 경우
		if(Integer.parseInt(startDate) > Integer.parseInt(endDate)) {
			throw new CustomException(ErrorCode.INVALID_DATE_RANGE);
		}
		
		List<GetWithdrawlRankingOutDto> withdrawlRanking = getWithdrawlRankingMapper.selectWithdrawlRanking(input);
		
		//조회결과가 없을 경우
		if(CollectionUtils.isEmpty(withdrawlRanking)) {
			throw new CustomException(ErrorCode.ACCT_HIST_NOT_FOUND);
		}
		
		return withdrawlRanking;
	}

}
