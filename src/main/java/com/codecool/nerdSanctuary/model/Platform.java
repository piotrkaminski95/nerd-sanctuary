package com.codecool.nerdSanctuary.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "platforms")
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    public  Platform() {}

    public Platform(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Platform(String name) {
        this.name = name;
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

    public Platform update(Platform updatedPlatform) {
        Platform newPlatform = new Platform();
        newPlatform.setId(updatedPlatform.getId());
        newPlatform.setName(updatedPlatform.getName());
        return newPlatform;
    }

    @Override
    public String toString() {
        return "Platform{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Platform platform = (Platform) o;
        return getName().equals(platform.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
