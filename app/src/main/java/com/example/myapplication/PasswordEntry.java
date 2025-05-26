package com.example.myapplication
        ;

import java.io.Serializable;

public class PasswordEntry implements Serializable {
    private int id;
    private String service;
    private String username;
    private String password;
    private String notes;

    public PasswordEntry(int id, String service, String username, String password, String notes) {
        this.id = id;
        this.service = service;
        this.username = username;
        this.password = password;
        this.notes = notes;
    }

    // Геттеры и сеттеры
    public int getId() { return id; }
    public String getService() { return service; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getNotes() { return notes; }
}