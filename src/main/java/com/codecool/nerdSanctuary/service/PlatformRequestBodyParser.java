package com.codecool.nerdSanctuary.service;

import com.codecool.nerdSanctuary.model.Platform;
import com.codecool.nerdSanctuary.repository.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PlatformRequestBodyParser implements RequestBodyParser<Platform> {
    @Autowired
    private PlatformRepository platformRepo;

    @Override
    public Platform parseBody(Map<String, Object> data) throws IllegalArgumentException, ClassCastException {
        Platform p = new Platform();
        p.setName((String) data.get("name"));
        if (platformRepo.existsByName(p.getName())) {
            return platformRepo.findByName(p.getName());
        }
        return p;
    }
}
