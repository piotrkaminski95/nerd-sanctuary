package com.codecool.nerdSanctuary.repository;

import com.codecool.nerdSanctuary.model.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    List<Game> findAll();
}