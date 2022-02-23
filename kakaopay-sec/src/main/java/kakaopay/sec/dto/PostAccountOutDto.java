package kakaopay.sec.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostAccountOutDto {
	
	@ApiModelProperty(value = "성공시 결과 메시지")
	private String result;
	
}
