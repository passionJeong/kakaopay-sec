package kakaopay.sec.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(value = "GetUserListOutDto", description = "사용자 리스트 조회 out Dto")
@Getter
public class GetUserListOutDto {
	
	@ApiModelProperty(value = "ID")
	private String userId;
	
	@ApiModelProperty(value = "이름")
	private String userName;
	
	@ApiModelProperty(value = "나이")
	private Integer userAge;
	
	@ApiModelProperty(value = "가입날짜")
	private String userJoinDate;
	
}
