package neusoft.controller;

import com.neusoft.ui.api.OptionDataApi;
import com.neusoft.ui.bean.EmptyDto;
import com.neusoft.ui.bean.RetryForm;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import neusoft.Exception.ExceptionClass.ResponseStatusException;
import neusoft.Exception.ExceptionClass.SQLException;
import neusoft.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@Log4j2
@CrossOrigin
public class OptionDataController implements OptionDataApi {
    @Autowired
    private JobService jobService;
    @Override
    @SneakyThrows
    public ResponseEntity<EmptyDto> optionData(@Valid RetryForm body) {
        try {
            jobService.optionData(body.getCondition());
        } catch (SQLException e) {
            throw ResponseStatusException.builder()
                    .errorCode(e.getCode())
                    .reason(e.getMessage())
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .cause(e)
                    .build();
        }
        return ResponseEntity.ok(new EmptyDto());
    }
}
