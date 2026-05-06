package com.example.demo.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;
    private String email;

    @OneToMany(mappedBy = "owner")
    private List<Bicycle> bicycles = new ArrayList<>();

    public void addBicycle(Bicycle bicycle) {
        bicycles.add(bicycle);
        bicycle.setOwner(this);
    }

    public void removeBike(Bicycle bicycle) {
        bicycles.remove(bicycle);
        bicycle.setOwner(null);
    }

    // =========================
    // GETTERS & SETTERS
    // =========================

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Bicycle> getBicycles() {
        return bicycles;
    }

    public void setBicycles(List<Bicycle> bicycles) {
        this.bicycles = bicycles;
    }
}