package neusoft.controller;

import com.neusoft.ui.api.SearchOptionApi;
import com.neusoft.ui.bean.*;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import neusoft.Exception.ExceptionClass.LoginException;
import neusoft.Exception.ExceptionClass.ResponseStatusException;
import neusoft.Exception.ExceptionClass.SQLException;
import neusoft.service.JobService;
import neusoft.transfer.DBTrafer;
import neusoft.transfer.TableDataTransfer;
import neusoft.transfer.TableTransfer;
import neusoft.transfer.UrlTraffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@CrossOrigin
@Log4j2
public class DBSearchController implements SearchOptionApi {
    @Autowired
    JobService jobService;

    private Integer index = 0;
    @Override
    @SneakyThrows
    public ResponseEntity<DBListDto> searchDB() {
        DBListDto dbListDto = new DBListDto();
        jobService.searchDBName().forEach(daName -> {
            dbListDto.add(new DBTrafer(daName).toDto());
        });
        return ResponseEntity.ok(dbListDto);
    }

    @Override
    public ResponseEntity<TableListDto> searchDBTable(String dbName) {
        TableListDto tableListDto = new TableListDto();
        jobService.searchDBTable(dbName).forEach(tableDate -> {
            tableListDto.add(new TableTransfer(tableDate).toDto());
        });
        return ResponseEntity.ok(tableListDto);
    }

    @Override
    @SneakyThrows
    public ResponseEntity<TableDataListDto> searchDBTableData(@Valid RetryForm body) {
        TableDataListDto tableDataListDto = new TableDataListDto();
        try {
            jobService.searchDBTableDate(body.getCondition()).forEach(stringObjectMap -> {
                index++;
                stringObjectMap.forEach((tableColumn, tableRow) -> {
                    tableDataListDto.add(new TableDataTransfer(tableColumn, tableRow, index).toDto());
                });
            });
        } catch (SQLException e) {
            throw ResponseStatusException.builder()
                    .errorCode(e.getCode())
                    .reason(e.getMessage())
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .cause(e)
                    .build();
        } catch (LoginException e) {
            throw ResponseStatusException.builder()
                    .errorCode(e.getCode())
                    .reason(e.getMessage())
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .cause(e)
                    .build();
        } finally {
            index = 0;
        }
        return ResponseEntity.ok(tableDataListDto);
    }

    @Override
    public ResponseEntity<UrlListDto> searchDBUrl() {
        UrlListDto urlListDto = new UrlListDto();
        jobService.searchDBUrl().forEach(urlAddress -> {
            urlListDto.add(new UrlTraffer(urlAddress.getId(), urlAddress.getUrl()).toDto());
        });
        return ResponseEntity.ok(urlListDto);
    }

}
