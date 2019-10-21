package com.neusoft.service;

import com.neusoft.domain.UrlAddress;
import com.neusoft.repository.UrlRepository;
import com.neusoft.utils.IdWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrlService {
    @Autowired
    UrlRepository urlRepository;
    @Autowired
    IdWork idWork;

    public UrlAddress add(String url){
        UrlAddress urlAddress = new UrlAddress();
        urlAddress.setUrl(url);
        urlAddress.setId(idWork.createId());
        return urlRepository.save(urlAddress);
    }

    public void deleteById(String id){
        urlRepository.deleteById(id);
    }

    public UrlAddress update(UrlAddress url){
        return urlRepository.save(url);
    }

    public List<UrlAddress> findAll(){
        return urlRepository.findAll();
    }

    public String findUrl(String url){
        return this.urlRepository.findByUrl(url);
    }

}
