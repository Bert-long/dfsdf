package com.neusoft.controller;

import com.neusoft.Exception.AddException;
import com.neusoft.domain.UrlAddress;
import com.neusoft.service.UrlService;
import com.neusoft.ui.api.DbManagerApi;
import com.neusoft.ui.bean.AddressDtoInner;
import com.neusoft.ui.bean.AddressListDto;
import com.neusoft.ui.bean.EmptyDto;
import com.neusoft.ui.bean.RetryForm;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.neusoft.Exception.ErrorMessage.AddError;

@RestController
@CrossOrigin
@Log4j2
public class DBManagerController implements DbManagerApi {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UrlService urlService;

    @Override
    public ResponseEntity<EmptyDto> addUrl(@Valid RetryForm body) {
        valeExistUrl(body.getUrl());
        urlService.add(body.getUrl());
        return ResponseEntity.ok(new EmptyDto());
    }

    @Override
    public ResponseEntity<EmptyDto> deleteUrl(String id) {
        urlService.deleteById(id);
        return ResponseEntity.ok(new EmptyDto());
    }

    @Override
    public ResponseEntity<EmptyDto> updateUrl(@Valid RetryForm body) {
        UrlAddress urlAddress = new UrlAddress();
        urlAddress.setId(body.getId());
        urlAddress.setUrl(body.getUrl());
        urlService.update(urlAddress);
        return ResponseEntity.ok(new EmptyDto());
    }

    @Override
    @SneakyThrows
    public ResponseEntity<AddressListDto> search() {
        AddressListDto addressListDto = new AddressListDto();
        urlService.findAll().forEach(urlAddress -> {
            AddressDtoInner addressDtoInner = modelMapper.map(urlAddress, AddressDtoInner.class);
            addressListDto.add(addressDtoInner);
        });
        return ResponseEntity.ok(addressListDto);
    }
    protected void valeExistUrl(String url){
        String vale = urlService.findUrl(url);
        if(vale != null){
            throw new AddException(AddError, AddError);
        }
    }
}
