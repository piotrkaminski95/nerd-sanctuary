package com.codecool.nerdSanctuary.repository;

import com.codecool.nerdSanctuary.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    boolean existsByName(String name);

    Developer findByName(String name);

    //TODO here should be overriden delete() method with proper annotation for safe delete not in entity
}
