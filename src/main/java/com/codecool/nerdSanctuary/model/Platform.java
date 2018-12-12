package com.codecool.nerdSanctuary.model;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@SQLDelete(sql = "UPDATE platform SET state = 'DELETED' WHERE id = ?", check = ResultCheckStyle.COUNT)
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

    @PreRemove
    public void deletePlatform(){
        //TODO add log info
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! its WORKING !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        this.state = State.DELETED;
    }

    @Override
    public String toString() {
        return "Platform{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
