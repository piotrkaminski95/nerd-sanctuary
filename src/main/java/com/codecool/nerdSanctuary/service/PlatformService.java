package com.codecool.nerdSanctuary.service;

import com.codecool.nerdSanctuary.exceptions.ResourceNotFoundException;
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
        logger.info("CRUD operation: READ ALL Platform");
        return repository.findAll();
    }


    public Platform getPlatform(long id) {
        logger.info(String.format("CRUD operation: READ Platform ID=%s", id));
        if (repository.exists(id)) {
            return repository.findOne(id);
        }
        throw new ResourceNotFoundException(String.format("Platform ID=%s does not exist", id));
    }


    public Platform savePlatform(Platform platform) {
        logger.info(String.format("CRUD operation: CREATE Platform=%s", platform));
        if (repository.existsByName(platform.getName())) {
            throw new ResourceNotFoundException(String.format("Platform %s does exist", platform.getName()));
        }
        return repository.save(platform);
    }


    public Platform updatePlatform(long id, Platform updatedPlatform) {
        logger.info(String.format("CRUD operation: UPDATE Platform ID=%s", id));
        return repository.save(getPlatform(id).update(updatedPlatform));
    }


    public List<Platform> deletePlatform(long id) {
        logger.info(String.format("CRUD operation: DELETE Platform ID=%s", id));
        repository.delete(id);
        return repository.findAll();
    }
}
