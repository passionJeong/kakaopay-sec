package kakaopay.sec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kakaopay.sec.dto.GetAccountHistoryListInDto;
import kakaopay.sec.dto.GetAccountHistoryListOutDto;
import kakaopay.sec.dto.GetAccountInfoOutDto;
import kakaopay.sec.dto.GetAllAccountHistoryListOutDto;
import kakaopay.sec.dto.PostAccountHistoryInDto;

@Mapper
public interface AccountHistoryMapper {
	
	//사전 조회(계좌 정보)
	GetAccountInfoOutDto selectAccountInfo(String accountNum);
	
	//계좌내역 신규
	int insertAccountHistory(PostAccountHistoryInDto history);
	
	//전체 계좌내역 조회
	List<GetAllAccountHistoryListOutDto> selectAllAccountHistoryList();
	
	//계좌내역 조회
	List<GetAccountHistoryListOutDto> selectAccountHistoryList(GetAccountHistoryListInDto accountInfo);

}
