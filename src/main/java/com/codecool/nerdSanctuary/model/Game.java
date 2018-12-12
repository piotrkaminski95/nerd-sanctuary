package com.codecool.nerdSanctuary.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@SQLDelete(sql = "UPDATE games SET state = 'DELETED' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "state <> 'DELETED'")
@NamedQuery(name = "Game.FindByTitle", query = "SELECT a FROM Game a WHERE a.title like :title ")
@Table(name = "games")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Enumerated
    private Genre genre;

    @Column
    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private State state;

    @Column(nullable = false)
    private Calendar releaseDate;

    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<Platform> platforms;

    @ManyToOne
    private Developer developer;

    public Game() {}

    public Game(long id, String title, Genre genre, Calendar releaseDate, List<Platform> platforms, Developer developer) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.platforms = platforms;
        this.developer = developer;
        this.state = State.ACTIVE;
    }

    public Game(String title, Genre genre, Calendar releaseDate, List<Platform> platforms, Developer developer) {
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.platforms = platforms;
        this.developer = developer;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @JsonFormat(pattern = "yyyy-M-dd")
    public Calendar getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Calendar releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    @PreRemove
    public void deleteGame(){
        //TODO add log info
        this.state = State.DELETED;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre=" + genre +
                ", releaseDate=" + releaseDate +
                ", platforms=" + platforms +
                ", developer=" + developer +
                '}';
    }
}
