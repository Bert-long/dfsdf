package neusoft.Exception.ExceptionClass;

import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;

public class ResponseStatusException
    extends org.springframework.web.server.ResponseStatusException {

  private String errorCode;

  private ResponseStatusException(
      HttpStatus status, String errorCode, String reason, Throwable cause) {
    super(status, reason, cause);
    Assert.notNull(errorCode, () -> "You must provide a error code.");
    this.errorCode = errorCode;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public static class ResponseStatusExceptionBuilder {
    private String errorCode;
    private HttpStatus httpStatus;
    private String reason;
    private Throwable cause;

    public ResponseStatusExceptionBuilder errorCode(@NonNull String errorCode) {
      this.errorCode = errorCode;
      return this;
    }

    public ResponseStatusExceptionBuilder httpStatus(@NonNull HttpStatus httpStatus) {
      this.httpStatus = httpStatus;
      return this;
    }

    public ResponseStatusExceptionBuilder reason(@NonNull String reason) {
      this.reason = reason;
      return this;
    }

    public ResponseStatusExceptionBuilder cause(Throwable cause) {
      this.cause = cause;
      return this;
    }

    public ResponseStatusException build() {
      return new ResponseStatusException(httpStatus, errorCode, reason, cause);
    }
  }

  public static ResponseStatusExceptionBuilder builder() {
    return new ResponseStatusExceptionBuilder();
  }
}
