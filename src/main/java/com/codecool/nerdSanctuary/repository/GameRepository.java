package com.codecool.nerdSanctuary.repository;

import com.codecool.nerdSanctuary.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findAll();
    Game findById(Long id);
    Game findByTitle(String title);

    boolean existsByTitle(String title);
}