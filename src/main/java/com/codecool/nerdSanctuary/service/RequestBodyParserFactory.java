package com.codecool.nerdSanctuary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RequestBodyParserFactory implements BodyParserFactory {
    @Autowired
    private GameRequestBodyParser gameRequestBodyParser;
    @Autowired
    private PlatformRequestBodyParser platformRequestBodyParser;
    @Autowired
    private DeveloperRequestBodyParser developerRequestBodyParser;

    public RequestBodyParserFactory() {}

    public RequestBodyParser getInstance(String className) throws ClassNotFoundException {
        switch (className) {
            case "gameRequestBodyParser": {
                return gameRequestBodyParser;
            }
            case "platformRequestBodyParser": {
                return platformRequestBodyParser;
            }
            case "developerRequestBodyParser": {
                return developerRequestBodyParser;
            }
            default: {
                throw new ClassNotFoundException();
            }
        }
    }
}
