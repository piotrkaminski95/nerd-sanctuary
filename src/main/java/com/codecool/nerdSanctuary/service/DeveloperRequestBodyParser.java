package com.codecool.nerdSanctuary.service;

import com.codecool.nerdSanctuary.model.Developer;
import com.codecool.nerdSanctuary.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DeveloperRequestBodyParser implements RequestBodyParser<Developer> {
    @Autowired
    private DeveloperRepository devRepo;

    @Override
    public Developer parseBody(Map<String, Object> data) throws IllegalArgumentException, ClassCastException {
        Developer d = new Developer();
        d.setName((String) data.get("name"));
        if (devRepo.existsByName(d.getName())) {
            return devRepo.findByName(d.getName());
        }
        d.setCountry((String) data.get("country"));
        return d;

    }
}
