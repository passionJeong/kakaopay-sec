package kakaopay.sec.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(value = "GetAccountBalanceAgeGroupOutDto", description = "연령대별 예치금 out dto")
@Getter
public class GetAccountBalanceAgeGroupOutDto {
	
	@ApiModelProperty(value = "연령대")
	private Integer ageGroup;
	
	@ApiModelProperty(value = "예치금")
	private Long accountBalance;
	
}
