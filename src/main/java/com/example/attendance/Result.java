package com.example.attendance;

public class Result<T> {
    private int code;        // 状态码（200成功，404未找到等）
    private String message;   // 提示信息
    private T data;          // 实际返回的数据

    // 无参构造
    public Result() {}

    // 有参构造
    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 成功响应（无数据）
    public static <T> Result<T> success() {
        return new Result<>(200, "success", null);
    }

    // 成功响应（带数据）
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "success", data);
    }

    // 成功响应（自定义消息）
    public static <T> Result<T> success(String message, T data) {
        return new Result<>(200, message, data);
    }

    // 失败响应（默认500）
    public static <T> Result<T> error(String message) {
        return new Result<>(500, message, null);
    }

    // 失败响应（自定义状态码）
    public static <T> Result<T> error(int code, String message) {
        return new Result<>(code, message, null);
    }

    // 参数错误（400）
    public static <T> Result<T> badRequest(String message) {
        return new Result<>(400, message, null);
    }

    // 未找到（404）
    public static <T> Result<T> notFound(String message) {
        return new Result<>(404, message, null);
    }

    // Getter 和 Setter
    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
}
