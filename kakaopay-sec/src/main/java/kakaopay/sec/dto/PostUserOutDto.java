package kakaopay.sec.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "PostUserOutDto", description = "사용자 신규 등록 in dto")
@Getter
@Setter
public class PostUserOutDto {
	
	@ApiModelProperty(value = "성공시 결과 메시지")
	private String result;
	
}
