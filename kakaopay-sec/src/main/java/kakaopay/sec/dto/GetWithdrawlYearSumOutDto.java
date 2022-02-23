package kakaopay.sec.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(value = "GetAccountBalanceYearSumOutDto", description = "년도 기준 예치금 총액 out dto")
@Getter
public class GetWithdrawlYearSumOutDto {
	
	@ApiModelProperty(value = "년도 기준 예치금 총액")
	private Long withdrawlYearSum;
	
}
