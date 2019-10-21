package com.neusoft.ui.api;

import com.neusoft.ui.bean.AddressListDto;
import com.neusoft.ui.bean.EmptyDto;
import com.neusoft.ui.bean.ErrorDto;
import com.neusoft.ui.bean.RetryForm;

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
public class DbManagerApiControllerIntegrationTest {

    @Autowired
    private DbManagerApi api;

    @Test
    public void addUrlTest() throws Exception {
        RetryForm body = new RetryForm();
        ResponseEntity<EmptyDto> responseEntity = api.addUrl(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void deleteUrlTest() throws Exception {
        String id = "id_example";
        ResponseEntity<EmptyDto> responseEntity = api.deleteUrl(id);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void searchTest() throws Exception {
        ResponseEntity<AddressListDto> responseEntity = api.search();
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void updateUrlTest() throws Exception {
        RetryForm body = new RetryForm();
        ResponseEntity<EmptyDto> responseEntity = api.updateUrl(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
