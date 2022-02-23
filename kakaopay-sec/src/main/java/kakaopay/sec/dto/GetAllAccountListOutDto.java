package kakaopay.sec.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(value = "GetAllAccountListOutDto", description = "전체계좌 리스트 조회 out Dto")
@Getter
public class GetAllAccountListOutDto {
	
	@ApiModelProperty(value = "ID")
	private String userId;
	
	@ApiModelProperty(value = "계좌번호")
	private String accountNum;
	
}
