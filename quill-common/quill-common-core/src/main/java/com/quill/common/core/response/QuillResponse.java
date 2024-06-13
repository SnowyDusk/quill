package com.quill.common.core.response;

import com.quill.common.core.constant.ResponseStatus;
import lombok.Getter;

@Getter
public class QuillResponse<T> {
    private final Integer code;
    private final String msg;
    private final T data;

    private QuillResponse(ResponseStatus status, T data) {
        code = status.getCode();
        msg = status.getMsg();
        this.data = data;
    }

    private QuillResponse(ResponseStatus status) {
        this(status, null);
    }

    private QuillResponse(T data) {
        this(ResponseStatus.SUCCESS, data);
    }

    private QuillResponse() {
        this(ResponseStatus.SUCCESS);
    }

    public static <T> QuillResponse<T> success(T data) {
        return new QuillResponse<>(data);
    }

    public static QuillResponse<Void> success() {
        return new QuillResponse<>();
    }

    public static <T> QuillResponse<T> error(ResponseStatus status, T data) {
        return new QuillResponse<>(status, data);
    }

    public static QuillResponse<Void> error() {
        return new QuillResponse<>(ResponseStatus.UNKNOWN_ERROR);
    }

    public static QuillResponse<Void> error(ResponseStatus status) {
        return new QuillResponse<>(status);
    }
}
