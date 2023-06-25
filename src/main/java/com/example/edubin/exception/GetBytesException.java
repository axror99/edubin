package com.example.edubin.exception;

import java.io.IOException;

public class GetBytesException extends RuntimeException {
    public GetBytesException(IOException e) {
        super(e);
    }
}
