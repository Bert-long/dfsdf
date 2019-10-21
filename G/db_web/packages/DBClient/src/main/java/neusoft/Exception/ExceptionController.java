package neusoft.Exception;

import com.neusoft.ui.bean.ErrorDto;
import lombok.extern.log4j.Log4j2;
import neusoft.Exception.ExceptionClass.ResponseStatusException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Log4j2
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ResponseStatusException.class)
    public ResponseEntity<ErrorDto> responseStatusException(ResponseStatusException ex) {
        log.error("Response status exception occurred.", ex);
        ErrorDto bean = new ErrorDto();
        bean.setCode(ex.getErrorCode());
        bean.setMessage(ex.getReason());
        return ResponseEntity.status(ex.getStatus()).body(bean);
    }


}
