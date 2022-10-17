package com.example.team7todo.handler.customexception;

import lombok.Getter;

@Getter
public class DataNotFoundException extends RuntimeException{

    private String field;

    public DataNotFoundException(String field, String message) {
        super(message);
        this.field = field;
    }
}
