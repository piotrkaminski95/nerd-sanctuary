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


    public Platform savePlatform(Platform platform) {
        logger.info(String.format("CRUD operation: CREATE Platform=%s", platform));
        return repository.save(platform);
    }


    public Platform updatePlatform(long id, Platform updatedPlatform) {
        logger.info(String.format("CRUD operation: UPDATE PLATFORM ID=%s", id));
        return repository.save(getPlatform(id).update(updatedPlatform));
    }
}
