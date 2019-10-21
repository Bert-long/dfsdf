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
public class OptionDataApiControllerIntegrationTest {

    @Autowired
    private OptionDataApi api;

    @Test
    public void optionDataTest() throws Exception {
        RetryForm body = new RetryForm();
        ResponseEntity<EmptyDto> responseEntity = api.optionData(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
