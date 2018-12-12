package com.codecool.nerdSanctuary.repository;

import com.codecool.nerdSanctuary.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, Long> {
    boolean existsByName(String name);

    Platform findByName(String name);
}
