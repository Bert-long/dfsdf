package com.neusoft.ui.api;

import com.neusoft.ui.bean.DBListDto;
import com.neusoft.ui.bean.ErrorDto;
import com.neusoft.ui.bean.RetryForm;
import com.neusoft.ui.bean.TableDataListDto;
import com.neusoft.ui.bean.TableListDto;
import com.neusoft.ui.bean.UrlListDto;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchOptionApiControllerIntegrationTest {

    @Autowired
    private SearchOptionApi api;

    @Test
    public void searchDBTest() throws Exception {
        ResponseEntity<DBListDto> responseEntity = api.searchDB();
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void searchDBTableTest() throws Exception {
        String dbName = "dbName_example";
        ResponseEntity<TableListDto> responseEntity = api.searchDBTable(dbName);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void searchDBTableDataTest() throws Exception {
        RetryForm body = new RetryForm();
        ResponseEntity<TableDataListDto> responseEntity = api.searchDBTableData(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void searchDBUrlTest() throws Exception {
        ResponseEntity<UrlListDto> responseEntity = api.searchDBUrl();
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
