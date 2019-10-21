package neusoft.Exception.ExceptionClass;

public class TaleInnerException extends RuntimeException{
    private String code;

    public TaleInnerException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
