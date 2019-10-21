package com.neusoft.Exception;

public class AddException extends RuntimeException {
    private String code;

    public AddException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
