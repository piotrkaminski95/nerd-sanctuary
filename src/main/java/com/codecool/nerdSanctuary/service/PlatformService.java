package com.codecool.nerdSanctuary.service;

import com.codecool.nerdSanctuary.model.Platform;
import com.codecool.nerdSanctuary.repository.PlatformRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatformService {

    @Autowired
    private PlatformRepository repository;

    private Logger logger = LogManager.getLogger();

    public List<Platform> getAllPlatform() {
        logger.info("CRUD operation: READ ALL PLATFORMS");
        return repository.findAll();
    }


    public Platform getPlatform(long id) {
        logger.info(String.format("CRUD operation: READ PLATFORM ID=%s", id));
        if (repository.exists(id)) {
            return repository.findOne(id);
        }
        throw new IllegalArgumentException(String.format("ID=%s does not exist", id));
    }
}
