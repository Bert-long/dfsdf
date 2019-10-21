package com.neusoft.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class IdWork {
    public String createId() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0,10);
    }
}
