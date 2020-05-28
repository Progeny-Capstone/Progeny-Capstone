package com.progeny.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group {

    // --------------------------------------------
    // -------------------BEAN---------------------
    // --------------------------------------------

    // ---------- TABLE SETUP -----------

    // 1. Create a primary key for the progeny_db table of groups
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 45)
    private String name;

    @Column(nullable = false, length = 120)
    private String description;


    @ManyToMany(mappedBy = "groupList")
    private List<User> users;

    // ---------- CONSTRUCTOR METHOD(S) -----------


    // ----- MEMORY SPACE CONSTRUCTOR ----
    public Group() {}

    // ----- COPY CONSTRUCTOR ----
    public Group(Group copy){
        id = copy.id;
        name = copy.name;
        description = copy.description;
    }
    //----------------------------


    // ----- WITH ID CONSTRUCTOR ----
    public Group(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // ----- WITHOUT ID CONSTRUCTOR ----
    public Group(String name, String description) {
        this.name = name;
        this.description = description;
    }




    // ---------- GET AND SET METHODS -----------


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
