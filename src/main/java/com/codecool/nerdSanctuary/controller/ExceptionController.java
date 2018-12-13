package com.codecool.nerdSanctuary.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.util.stream.Stream;

@ControllerAdvice
public class ExceptionController {

    private Logger logger = LogManager.getLogger();

    @Autowired
    private JavaMailSender javaMailSender;

    @ExceptionHandler(RuntimeException.class)
    public RuntimeException handleRunTimeException(RuntimeException exception, HttpServletResponse response) {
        String message = getMessage(exception, response);
        logger.error(message);
        if (response.getStatus() == 500) {
            sendEmail(exception, message);
        }
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


    public void sendEmail(RuntimeException exception, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom("testsendmail.nerdsanctuary@gmail.com");
        simpleMailMessage.setTo("testsendmail.nerdsanctuary@gmail.com");
        simpleMailMessage.setSubject("Server Error Occurs!");
        simpleMailMessage.setText(
                String.format(
                        "%s%nStack Trace: %n %s",
                        message,
                        getStringStackTrace(exception.getStackTrace())
                )
        );

        javaMailSender.send(simpleMailMessage);
    }


    private String getStringStackTrace(StackTraceElement[] stackTraceElements) {
        StringBuilder sb = new StringBuilder();
        Stream.of(stackTraceElements).forEach(stackTrace -> sb.append(stackTrace.toString() + "\n"));
        return sb.toString();
    }
}
