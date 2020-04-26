package com.springbootapps.petclinic.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OwnerTest {

    Owner testClass;
    String address;
    String city;
    String telephone;
    Set<Pet> pets;
    Pet pet1;
    Pet pet2;

    @BeforeEach
    void setUp() {
        address = "aAddress";
        city = "aCity";
        telephone = "aTelephone";
        pets = new HashSet<>(2);
        pet1 = Pet.builder().name("aPet1").build();
        pet2 = Pet.builder().name("aPet2").build();
        pets.add(pet1);
        pets.add(pet2);
        testClass = Owner.builder().address(address).city(city).telephone(telephone).pets(pets).build();
    }

    @AfterEach
    void tearDown() {
        address = null;
        city = null;
        telephone = null;
        testClass = null;
        pets = null;
        pet1 = null;
        pet2 = null;
    }

    @Test
    void getAddress() {
        String expected = address;
        assertEquals(expected, testClass.getAddress());
    }

    @Test
    void getCity() {
        String expected = city;
        assertEquals(expected, testClass.getCity());
    }

    @Test
    void getTelephone() {
        String expected = telephone;
        assertEquals(expected, testClass.getTelephone());
    }

    @Test
    void getPets() {
        int expectedSize = 2;
        Set<Pet> expected = new HashSet<>(pets);
        assertEquals(expectedSize, testClass.getPets().size());
        testClass.getPets().forEach(Assertions::assertNotNull);
        assertEquals(expected, testClass.getPets());
    }
}