package kakaopay.sec.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 400 BAD_REQUEST : 잘못된 요청 */
	USER_JOIN_FAIL(HttpStatus.BAD_REQUEST, "회원가입을 실패하였습니다."),
	ACCT_JOIN_FAIL(HttpStatus.BAD_REQUEST, "계좌등록을 실패하였습니다."),
	ACCT_HIST_JOIN_FAIL(HttpStatus.BAD_REQUEST, "계좌내역등록을 실패하였습니다."),
	DUPLICATE_ID(HttpStatus.BAD_REQUEST, "이미 회원가입되어있는 ID입니다."),
	DUPLICATE_ACCT(HttpStatus.BAD_REQUEST, "이미 등록되어있는 계좌번호입니다."),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "입력값을 확인해주세요."),
    INVALID_DATE_RANGE(HttpStatus.BAD_REQUEST, "시작일이 종료일보다 클 수 없습니다."),
    INVALID_YEAR(HttpStatus.BAD_REQUEST, "올바른 년도값을 입력해주세요."),
    INVALID_DATE(HttpStatus.BAD_REQUEST, "올바른 날짜값을 입력해주세요."),

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자 정보를 찾을 수 없습니다."),
    ACCT_NOT_FOUND(HttpStatus.NOT_FOUND, "계좌 정보를 찾을 수 없습니다."),
    ACCT_HIST_NOT_FOUND(HttpStatus.NOT_FOUND, "계좌내역을 찾을 수 없습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String detail;
}
