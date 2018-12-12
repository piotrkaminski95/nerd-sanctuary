package com.codecool.nerdSanctuary.model;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Objects;

@Entity
@SQLDelete(sql = "UPDATE platforms SET state = 'DELETED' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "state <> 'DELETED'")
@NamedQuery(name = "Platform.FindByName", query = "SELECT a FROM Platform a WHERE a.name like :name ")
@Table(name = "platforms")
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private State state;

    public  Platform() {}

    public Platform(long id, String name) {
        this.id = id;
        this.name = name;
        this.state = State.ACTIVE;
    }

    public Platform(String name) {
        this.name = name;
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
