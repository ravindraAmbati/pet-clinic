package com.springbootapps.petclinic.services.Map;

import com.springbootapps.petclinic.model.Owner;
import com.springbootapps.petclinic.services.OwnerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    OwnerService testClass;

    @BeforeEach
    void setUp() {
        testClass = new OwnerServiceMap();
    }

    @AfterEach
    void tearDown() {
        testClass = null;
    }

    @Test
    void findById() {
        Long id = 1L;
        Owner expected = Owner.builder().id(id).build();
        testClass.save(expected);
        Owner actual = testClass.findById(id);
        assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findAll() {
        Long id1 = 1L;
        Long id2 = 2L;
        Owner owner1 = Owner.builder().id(id1).build();
        Owner owner2 = Owner.builder().id(id2).build();
        Set<Owner> expected = new HashSet<>(2);
        expected.add(owner1);
        expected.add(owner2);
        testClass.save(owner1);
        testClass.save(owner2);
        Set<Owner> actual = testClass.findAll();
        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        actual.forEach(Assertions::assertNotNull);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void deleteById() {
        Long id = 1L;
        Owner expected = Owner.builder().id(id).build();
        testClass.save(expected);
        Owner actual = testClass.findById(id);
        assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
        testClass.deleteById(id);
        actual = testClass.findById(id);
        assertNull(actual);
    }

    @Test
    void delete() {
        Long id1 = 1L;
        Long id2 = 2L;
        Owner owner1 = Owner.builder().id(id1).build();
        Owner owner2 = Owner.builder().id(id2).build();
        Set<Owner> expected = new HashSet<>(2);
        expected.add(owner1);
        expected.add(owner2);
        testClass.save(owner1);
        testClass.save(owner2);
        Set<Owner> actual = testClass.findAll();
        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        actual.forEach(Assertions::assertNotNull);
        Assertions.assertEquals(expected, actual);
        testClass.delete(owner1);
        actual = testClass.findAll();
        assertNotNull(actual);
        assertEquals(1, actual.size());
        actual.forEach(Assertions::assertNotNull);
        actual.forEach(owner -> assertEquals(owner2, owner));
        testClass.delete(owner2);
        actual = testClass.findAll();
        assertEquals(0, actual.size());
    }

    @Test
    void save() {
        Long id = 1L;
        Owner expected = Owner.builder().id(id).build();
        testClass.save(expected);
        Owner actual = testClass.findById(id);
        assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findByLastName() {
        String lastName = "aName";
        Owner expected = Owner.builder().lastName(lastName).build();
        testClass.save(expected);
        assertThrows(UnsupportedOperationException.class, () -> {
            testClass.findByLastName(lastName);
        });
    }

    @Test
    void count() {
        Long id1 = 1L;
        Long id2 = 2L;
        Owner owner1 = Owner.builder().id(id1).build();
        Owner owner2 = Owner.builder().id(id2).build();
        Set<Owner> expected = new HashSet<>(2);
        expected.add(owner1);
        expected.add(owner2);
        testClass.save(owner1);
        testClass.save(owner2);
        assertEquals(expected.size(), testClass.count());
    }
}