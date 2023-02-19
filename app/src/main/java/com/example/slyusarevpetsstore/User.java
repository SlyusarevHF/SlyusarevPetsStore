package com.example.slyusarevpetsstore;

import java.util.List;

public class User {
    private Integer id;
    private String FName;
    private String LName;
    private String Uname;
    private String Email;
    private String Pword;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return FName;
    }

    public void setFirstName(String firstName) {
        this.FName = firstName;
    }

    public String getLastName() {
        return LName;
    }

    public void setLastName(String lastName) {
        this.LName = lastName;
    }

    public String getUsername() {
        return Uname;
    }

    public void setUsername(String username) {
        this.Uname = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPassword() {
        return Pword;
    }

    public void setPassword(String password) {
        this.Pword = password;
    }
}
