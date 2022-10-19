package com.example.team7todo.handler.customexception;

import lombok.Getter;

@Getter
public class NotAuthorException extends RuntimeException{
    private String field;

    public NotAuthorException(String field, String message) {
        super(message);
        this.field = field;
    }
}
