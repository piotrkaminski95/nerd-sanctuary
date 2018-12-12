package com.codecool.nerdSanctuary.repository;

import com.codecool.nerdSanctuary.model.Platform;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository extends CrudRepository<Platform, Long> {
    boolean existsByName(String name);

    Platform findByName(String name);
}
