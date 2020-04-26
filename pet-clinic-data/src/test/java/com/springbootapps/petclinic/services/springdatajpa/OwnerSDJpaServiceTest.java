package com.springbootapps.petclinic.services.springdatajpa;

import com.springbootapps.petclinic.model.Owner;
import com.springbootapps.petclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerSDJpaService testClass;

    Owner owner1;
    Owner owner2;
    Set<Owner> owners;
    Long id1;
    Long id2;
    String lastName;

    @BeforeEach
    void setUp() {
        id1 = 1L;
        id2 = 2L;
        lastName = "aName";
        owner1 = Owner.builder().id(id1).lastName(lastName).build();
        owner2 = Owner.builder().id(id2).lastName(lastName).build();
        owners = new HashSet<>(2);
        owners.add(owner1);
        owners.add(owner2);
    }

    @AfterEach
    void tearDown() {
        id1 = null;
        id2 = null;
        lastName = null;
        owner1 = null;
        owner2 = null;
        owners = null;
    }

    @Test
    void findByLastName() {
        //given
        Optional<Owner> expected = Optional.of(owner1);

        //when
        when(ownerRepository.findByLastName(lastName)).thenReturn(expected);

        //this
        assertEquals(expected.orElse(null), testClass.findByLastName(lastName));
    }

    @Test
    void findById() {
        //given
        Long id = 1L;
        Optional<Owner> expected = Optional.of(owner1);

        //when
        when(ownerRepository.findById(id)).thenReturn(expected);

        //this
        assertEquals(expected.orElse(null), testClass.findById(id));
    }

    @Test
    void findAll() {
        //given
        Set<Owner> expected = this.owners;

        //when
        when(ownerRepository.findAll()).thenReturn(owners);

        //this
        assertEquals(expected.size(), testClass.findAll().size());
        testClass.findAll().forEach(Assertions::assertNotNull);
        assertEquals(expected, testClass.findAll());
    }

    @Test
    void save() {
        //given
        Owner expected = owner1;

        //when
        when(ownerRepository.save(owner1)).thenReturn(owner1);

        //this
        assertNotNull(testClass.save(owner1));
        assertEquals(expected, testClass.save(owner1));
        testClass.findAll().forEach(Assertions::assertNotNull);
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void count() {
        //given
        Long expected = 2L;

        //when
        when(ownerRepository.count()).thenReturn(expected);

        //this
        assertEquals(expected, testClass.count());
    }
}