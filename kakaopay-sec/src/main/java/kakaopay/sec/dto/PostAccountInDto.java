package kakaopay.sec.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "PostAccountInDto", description = "계좌 신규 등록 in dto")
@Setter
@Getter
public class PostAccountInDto {
	
	@ApiModelProperty(value = "ID" , example = "3")
	@NotNull(message = "ID를 입력해주세요.")
	@Size(min = 1, max = 20, message = "ID의 길이는 1~20자입니다.")
	private String userId;
	
	@ApiModelProperty(value = "계좌번호" , example = "1111-01")
	@NotNull(message = "계좌번호를 입력해주세요.")
	@Size(min = 7, max = 7, message = "계좌번호의 길이는 '-'포함하여 7자입니다.")
	private String accountNum;
	
}
