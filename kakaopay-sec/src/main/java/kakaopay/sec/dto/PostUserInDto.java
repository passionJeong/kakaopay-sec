package kakaopay.sec.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "PostUserInDto", description = "사용자 신규 등록 in dto")
@Setter
@Getter
public class PostUserInDto {
	
	@ApiModelProperty(value = "ID" , example = "kakaopaySec123")
	@NotNull(message = "ID를 입력해주세요.")
	@Size(min = 1, max = 20, message = "ID의 길이는 1~20자입니다.")
	private String userId;
	
	@ApiModelProperty(value = "이름" , example = "카카오페이증권")
	@NotNull(message = "이름을 입력해주세요.")
	@Size(min = 1, max = 20, message = "이름의 길이는 1~20자입니다.")
	private String userName;
	
	@ApiModelProperty(value = "나이" , example = "2")
	@NotNull(message = "나이를 입력해주세요.")
	private Integer userAge;
	
}
