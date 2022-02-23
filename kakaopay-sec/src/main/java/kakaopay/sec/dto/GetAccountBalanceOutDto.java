package kakaopay.sec.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(value = "GetAccountBalanceOutDto", description = "계좌별 예치금 out dto")
@Getter
public class GetAccountBalanceOutDto {
	
	@ApiModelProperty(value = "계좌번호")
	private String accountNum;
	
	@ApiModelProperty(value = "예치금")
	private Long accountBalance;
	
}
