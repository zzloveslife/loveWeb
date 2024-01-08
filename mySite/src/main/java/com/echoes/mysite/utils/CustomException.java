package com.echoes.mysite.utils;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {
    private int errorCode;
    private String errorMessage;

    public CustomException(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
