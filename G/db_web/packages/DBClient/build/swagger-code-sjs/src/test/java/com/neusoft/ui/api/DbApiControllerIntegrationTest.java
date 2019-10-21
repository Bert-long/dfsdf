package com.neusoft.ui.api;

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
public class DbApiControllerIntegrationTest {

    @Autowired
    private DbApi api;

    @Test
    public void loginTest() throws Exception {
        RetryForm body = new RetryForm();
        ResponseEntity<EmptyDto> responseEntity = api.login(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void logoutTest() throws Exception {
        ResponseEntity<EmptyDto> responseEntity = api.logout();
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
