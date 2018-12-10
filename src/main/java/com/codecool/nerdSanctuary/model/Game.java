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
    private Date releseDate;

    @OneToMany
    private List<Platform> platforms;

    @OneToOne
    private Developer developer;

    public Game() {}

    public Game(long id, String title, Genre genre, Date releseDate, List<Platform> platforms, Developer developer) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.releseDate = releseDate;
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

    public Date getReleseDate() {
        return releseDate;
    }

    public void setReleseDate(Date releseDate) {
        this.releseDate = releseDate;
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
                ", releseDate=" + releseDate +
                ", platforms=" + platforms +
                ", developer=" + developer +
                '}';
    }
}
