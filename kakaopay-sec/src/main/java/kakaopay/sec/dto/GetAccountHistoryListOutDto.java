package kakaopay.sec.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(value = "GetAccountHistoryListOutDto", description = "계좌내역 조회 out Dto")
@Getter
public class GetAccountHistoryListOutDto {
	
	@ApiModelProperty(value = "계좌번호")
	private String accountNum;
	
	@ApiModelProperty(value = "입출금여부")
	private String withdrawlYn;
	
	@ApiModelProperty(value = "입금액")
	private Long withdrawlAmount;
	
	@ApiModelProperty(value = "입금일")
	private String withdrawlDate;
	
}
