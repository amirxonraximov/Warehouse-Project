package com.example.appwarehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private String message;
    private boolean success;
    private Object data;

    public Result(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public static Result success(String message, Object data) {
        return new Result(message, true, data);
    }

    public static Result fail(String message, Object data) {
        return new Result(message, false, data);
    }

    public static Result success(String message) {
        return new Result(message, true);
    }

    public static Result success(Object data) {
        return new Result("Success", true, data);
    }

    public static Result fail(String message) {
        return new Result(message, false);
    }
}
