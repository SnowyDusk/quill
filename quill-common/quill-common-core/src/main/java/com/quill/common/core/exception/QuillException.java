package com.quill.common.core.exception;

import com.quill.common.core.constant.ResponseStatus;
import lombok.Getter;

@Getter
public class QuillException extends RuntimeException{
    private final ResponseStatus status;

    public QuillException(ResponseStatus status) {
        this.status = status;
    }
}
