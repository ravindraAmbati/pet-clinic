package com.springbootapps.petclinic.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;

@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        HashSet<Visit> singleVisit = new HashSet<>(1);
        pet.setVisits(singleVisit);
        this.pet = pet;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "date=" + date +
                ", description='" + description + '\'' +
                ", pet=" + pet +
                "} " + super.toString();
    }
}
