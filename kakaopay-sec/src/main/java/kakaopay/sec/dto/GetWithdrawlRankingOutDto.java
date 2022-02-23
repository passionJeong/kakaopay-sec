package kakaopay.sec.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(value = "GetWithdrawlRankingOutDto", description = "사용자별 예치금 랭킹 out dto")
@Getter
public class GetWithdrawlRankingOutDto {
	
	@ApiModelProperty(value = "ID")
	private String userId;
	
	@ApiModelProperty(value = "이름")
	private String userName;
	
	@ApiModelProperty(value = "예치금")
	private Long withdrawlAmount;
	
}
