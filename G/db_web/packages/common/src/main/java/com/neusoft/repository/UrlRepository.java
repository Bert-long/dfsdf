package com.neusoft.repository;

import com.neusoft.domain.UrlAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UrlRepository extends JpaRepository<UrlAddress,String> , JpaSpecificationExecutor<UrlAddress> {
        String findByUrl(String url);
}
