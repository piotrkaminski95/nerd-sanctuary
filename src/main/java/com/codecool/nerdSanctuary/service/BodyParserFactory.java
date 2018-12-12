package com.codecool.nerdSanctuary.service;

import org.springframework.stereotype.Component;

@Component
public interface BodyParserFactory {
    RequestBodyParser getInstance(String className) throws ClassNotFoundException;
}
