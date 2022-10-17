package com.example.team7todo.handler.customexception;

import lombok.Getter;

@Getter
public class LoginException extends RuntimeException{

    String field;

    public LoginException(String field, String message) {
        super(message);
        this.field = field;
    }



}
