package com.example.team7todo.handler.customexception;

public class WrongInputException extends RuntimeException{
    public WrongInputException(String message) {
        super(message);
    }

}
