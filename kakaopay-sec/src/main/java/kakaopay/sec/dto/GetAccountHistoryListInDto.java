package kakaopay.sec.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "GetAccountHistoryListInDto", description = "계좌내역 조회 In Dto")
@Setter
@Getter
public class GetAccountHistoryListInDto {
	
	@ApiModelProperty(value = "계좌번호" , example = "1111-01")
	@NotNull(message = "계좌번호를 입력해주세요.")
	@Size(min = 7, max = 7, message = "계좌번호의 길이는 '-'포함하여 7자입니다.")
	private String accountNum;
	
}
