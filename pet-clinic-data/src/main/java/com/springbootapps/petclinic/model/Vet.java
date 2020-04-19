package com.springbootapps.petclinic.model;

import java.util.Set;

public class Vet extends Person {

    private Set<Specialist> specialists;

    public Set<Specialist> getSpecialists() {
        return specialists;
    }

    public void setSpecialists(Set<Specialist> specialists) {
        this.specialists = specialists;
    }
}
