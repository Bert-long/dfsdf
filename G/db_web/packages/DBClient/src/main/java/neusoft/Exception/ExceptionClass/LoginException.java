package neusoft.Exception.ExceptionClass;

public class LoginException extends RuntimeException {

  private String code;

  public LoginException(String code, String message) {
    super(message);
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}
