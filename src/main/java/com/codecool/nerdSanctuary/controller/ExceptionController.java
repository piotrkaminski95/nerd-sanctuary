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
        logger.error(exception.getClass().getSimpleName() + "- CODE= " + response.getStatus() + " - " + exception.getMessage());
        return exception;
    }
}
