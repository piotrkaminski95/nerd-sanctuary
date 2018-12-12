package com.codecool.nerdSanctuary.repository;

import com.codecool.nerdSanctuary.model.Developer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends CrudRepository<Developer, Long> {
    boolean existsByName(String name);

    Developer findByName(String name);
}
