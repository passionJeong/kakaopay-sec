package kakaopay.sec.exception;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String code;
    private final String message;

    public static ResponseEntity<Object> toResponseEntity(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .status(errorCode.getHttpStatus().value())
                        .error(errorCode.getHttpStatus().name())
                        .code(errorCode.name())
                        .message(errorCode.getDetail())
                        .build()
                        );
    }
    
    public static ResponseEntity<Object> toResponseEntity(ErrorCode errorCode, BindingResult bindingResult) {
    	return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .status(errorCode.getHttpStatus().value())
                        .error(errorCode.getHttpStatus().name())
                        .code(errorCode.name())
                        .message(bindingResult.getFieldError().getDefaultMessage())
                        .build()
                        );
    }
    
}