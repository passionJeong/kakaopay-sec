package kakaopay.sec.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "PostAccountHistoryInDto", description = "계좌내역 등록 in dto")
@Setter
@Getter
public class PostAccountHistoryInDto {
	
	@ApiModelProperty(value = "계좌번호", example = "1000-01")
	@NotNull(message = "계좌번호를 입력해주세요.")
	@Size(min = 7, max = 7, message = "계좌번호의 길이는 '-'포함  7자입니다.")
	private String accountNum;
	
	@ApiModelProperty(value = "입출금여부", example = "Y")
	@NotNull(message = "입출금여부를 입력해주세요.")
	@Size(min = 1, max = 1, message = "입출금여부는 'Y'또는 'N'입니다.")
	private String withdrawlYn;
	
	@ApiModelProperty(value = "입금액", example = "123456")
	@NotNull(message = "입금액을 입력해주세요.")
	private Long withdrawlAmount;
	
	@ApiModelProperty(value = "입금일", example = "2022-02-22")
	private String withdrawlDate;
	
}
