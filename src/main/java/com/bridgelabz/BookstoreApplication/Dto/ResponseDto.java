package com.bridgelabz.BookstoreApplication.Dto;

import lombok.ToString;

@ToString
public class ResponseDto {
    public Object data;
    public String message;
    public ResponseDto(String message, Object data) {
        this.data = data;
        this.message = message;
    }
}

