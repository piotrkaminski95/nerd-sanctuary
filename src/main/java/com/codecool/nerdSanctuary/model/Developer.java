package com.codecool.nerdSanctuary.model;

import com.codecool.nerdSanctuary.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@SQLDelete(sql = "UPDATE developers SET state = 'DELETED' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "state <> 'DELETED'")
@NamedQuery(name = "Developer.FindByName", query = "SELECT a FROM Developer a WHERE a.name like :name ")
@Table(name = "developers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private State state;

    @Column(nullable = false)
    private String country;

    @OneToMany(mappedBy = "developer", cascade = {CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JsonIgnore
    @ElementCollection
    private List<Game> games;

    public Developer() {
        this.state = State.ACTIVE;
    }

    public Developer(long id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.state = State.ACTIVE;
    }

    public Developer(String name, String country) {
        this.name = name;
        this.country = country;
        this.state = State.ACTIVE;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public void addGame(Game game) {
        game.setDeveloper(this);
        games.add(game);
    }

    public Developer update(Developer updatedDev) {
        Developer newDevWithUpdatedValues = new Developer();
        newDevWithUpdatedValues.setId(updatedDev.getId());
        newDevWithUpdatedValues.setCountry(updatedDev.getCountry());
        newDevWithUpdatedValues.setName(updatedDev.getName());
        return newDevWithUpdatedValues;
    }


    public void removeGame(long gameId) {
        Game toRemove = getGame(gameId);
        toRemove.setDeveloper(null);
        games.remove(toRemove);
    }


    public Game getGame(long gameId) {
        return checkAndGetGameFromOptional(games.stream().filter(game -> game.getId() == gameId).findFirst());
    }


    private Game checkAndGetGameFromOptional(Optional<Game> toCheck) {
        if (toCheck.isPresent()) {
            return toCheck.get();
        }
        throw new ResourceNotFoundException("Game not found");
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
