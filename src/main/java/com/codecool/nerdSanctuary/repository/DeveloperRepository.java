package com.codecool.nerdSanctuary.repository;

import com.codecool.nerdSanctuary.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
}
