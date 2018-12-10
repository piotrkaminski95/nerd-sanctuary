package com.codecool.nerdSanctuary.model;

import javax.persistence.*;

@Entity
@Table(name = "platforms")
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;
}
