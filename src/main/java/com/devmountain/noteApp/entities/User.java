package com.devmountain.noteApp.entities;

import com.devmountain.noteApp.dtos.UserDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jdk.jfr.SettingDefinition;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


//entites tells what class is being mapped to a data source
@Entity

//
@Table (name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //private members variable only accessible within the class the reside
    // to access you must use getters and setters
    @Column(unique = true)
    private String username;

    @Column
    private String password;

    public Long getId() {
        return id;
    }
//used to create the schema
    @OneToMany(mappedBy ="user",fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    private Set<Note>noteSet = new HashSet<>();

//constructors help create the objects

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    // no argument contructor
    public User() {
    }
    //all argument constructor
    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    // logic for database
    public User(UserDto userDto){
        if(userDto.getUsername() !=null){
            this.username = userDto.getUsername();
        }
        if(userDto.getPassword() !=null){
            this.password =userDto.getPassword();
        }
    }
}
