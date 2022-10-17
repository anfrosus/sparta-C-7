package com.example.team7todo.dto.response;

import com.example.team7todo.handler.MyError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto<T> {

    private boolean success;
    private T data;
    private MyError myError;

    public static <T> ResponseDto<T> success(T data) {
        return new ResponseDto<>(true, data, null);
    }

    public static <T> ResponseDto<T> fail(String myStatus, String myMessage) {
        return new ResponseDto<>(false, null, new MyError(myStatus, myMessage));
    }
}
