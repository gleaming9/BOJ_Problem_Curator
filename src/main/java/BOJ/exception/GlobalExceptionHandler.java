package BOJ.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e){
        return ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }

    @ExceptionHandler(CustomRuntimeException.class)
    public ResponseEntity<String> handleCustomRuntime(RuntimeException e){
        return ResponseEntity
                .internalServerError()
                .body(e.getMessage());
    }

    // 예상치 못한 오류 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnexpected(Exception e) {
        return ResponseEntity
                .internalServerError()
                .body(ErrorMessage.UNKNOWN_ERROR.getMessage());
    }
}
