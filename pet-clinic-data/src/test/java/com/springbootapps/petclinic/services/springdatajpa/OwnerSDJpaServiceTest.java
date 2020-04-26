package com.springbootapps.petclinic.services.springdatajpa;

import com.springbootapps.petclinic.model.Owner;
import com.springbootapps.petclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerSDJpaService testClass;

    Owner owner;
    Long id;
    String lastName;

    @BeforeEach
    void setUp() {
        id = 1L;
        lastName = "aName";
        owner = Owner.builder().id(id).lastName(lastName).build();
    }

    @AfterEach
    void tearDown() {
        owner = null;
    }

    @Test
    void findByLastName() {
        //given
        Optional<Owner> expected = Optional.of(owner);

        //when
        when(ownerRepository.findByLastName(lastName)).thenReturn(expected);

        //this
        assertEquals(expected.orElse(null), testClass.findByLastName(lastName));
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void count() {
    }
}