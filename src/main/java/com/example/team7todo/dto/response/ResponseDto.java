package com.example.team7todo.dto.response;

import com.example.team7todo.dto.MyError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto<T> {
    private boolean success;
    private T data;
    private MyError error;

    public static <T> ResponseDto<T> success(T data) {
        return new ResponseDto<>(true, data, null);
    }

    public static <T> ResponseDto<T> fail(String code, String message) {
        return new ResponseDto<>(false, null, new MyError(code, message));
    }
}
