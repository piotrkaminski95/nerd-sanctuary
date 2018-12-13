package com.codecool.nerdSanctuary.controller;

import com.codecool.nerdSanctuary.exceptions.ResourceNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    private Logger logger = LogManager.getLogger();

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResourceNotFoundException handleResourceNotFound(ResourceNotFoundException exception) {
        logger.error(exception.toString() + " - " + exception.getMessage());
        return exception;
    }
}
