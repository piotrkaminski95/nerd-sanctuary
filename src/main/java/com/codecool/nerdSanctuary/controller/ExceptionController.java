package com.codecool.nerdSanctuary.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    private Logger logger = LogManager.getLogger();

    @ExceptionHandler(RuntimeException.class)
    public RuntimeException handleRunTimeException(RuntimeException exception) {
        logger.error(exception.getClass().getSimpleName() + " - " + exception.getMessage());
        return exception;
    }
}
