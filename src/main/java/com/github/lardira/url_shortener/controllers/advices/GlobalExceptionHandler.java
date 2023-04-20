package com.github.lardira.url_shortener.controllers.advices;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    static public final String ERROR_VIEW = "error";

    @ExceptionHandler
    public String handleException(Exception e) {
        return ERROR_VIEW;
    }
}
