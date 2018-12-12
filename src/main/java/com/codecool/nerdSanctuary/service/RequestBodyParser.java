package com.codecool.nerdSanctuary.service;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface RequestBodyParser<T> {
    T parseBody(Map<String, Object> data) throws IllegalArgumentException, ClassNotFoundException;
}
