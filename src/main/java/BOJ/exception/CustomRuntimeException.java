package BOJ.exception;

public class CustomRuntimeException extends RuntimeException{
    public CustomRuntimeException(ErrorMessage errorMessage, Throwable cause){
        super(errorMessage.getMessage(), cause);
    }

    public CustomRuntimeException(ErrorMessage errorMessage, String data){
        super(errorMessage.getMessage() + data);
    }
}
