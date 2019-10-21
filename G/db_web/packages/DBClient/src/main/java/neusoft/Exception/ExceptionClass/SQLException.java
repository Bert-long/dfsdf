package neusoft.Exception.ExceptionClass;

public class SQLException  extends RuntimeException {
    private String code;

    public SQLException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
