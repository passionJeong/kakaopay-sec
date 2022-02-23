package kakaopay.sec.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "PostAccountHistoryOutDto", description = "계좌내역 추가 out Dto")
@Getter
@Setter
public class PostAccountHistoryOutDto {
	
	@ApiModelProperty(value = "성공시 결과 메시지")
	private String result;
	
}
