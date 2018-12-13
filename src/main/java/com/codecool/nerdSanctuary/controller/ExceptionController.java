package com.codecool.nerdSanctuary.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ExceptionController {

    private Logger logger = LogManager.getLogger();

    @ExceptionHandler(RuntimeException.class)
    public RuntimeException handleRunTimeException(RuntimeException exception, HttpServletResponse response) {
        logger.error(getMessage(exception, response));
        return exception;
    }


    private String getMessage(RuntimeException exception, HttpServletResponse response) {
        return String.format(
                "%s - STATUS CODE= %s - %s",
                exception.getClass().getSimpleName(),
                response.getStatus(),
                exception.getMessage()
        );
    }
}
