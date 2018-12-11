package com.codecool.nerdSanctuary.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String title;

    @Enumerated
    private Genre genre;

    @Column(nullable = false)
    private Date releaseDate;

    @ManyToMany
    private List<Platform> platforms;

    @ManyToOne
    private Developer developer;

    public Game() {}

    public Game(long id, String title, Genre genre, Date releaseDate, List<Platform> platforms, Developer developer) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.platforms = platforms;
        this.developer = developer;
    }

    public Game(String title, Genre genre, Date releaseDate, List<Platform> platforms, Developer developer) {
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.platforms = platforms;
        this.developer = developer;
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
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
