package com.springbootapps.petclinic.controllers;

import com.springbootapps.petclinic.model.Owner;
import com.springbootapps.petclinic.model.Pet;
import com.springbootapps.petclinic.model.PetType;
import com.springbootapps.petclinic.services.OwnerService;
import com.springbootapps.petclinic.services.PetService;
import com.springbootapps.petclinic.services.PetTypeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PetControllerTest {

    private static final String VIEW_PET_CREATION_FORM = "pets/createOrUpdatePetForm";

    @Mock
    OwnerService ownerService;

    @Mock
    PetService petService;

    @Mock
    PetTypeService petTypeService;

    @InjectMocks
    PetController testClass;

    @Mock
    Model model;

    Set<Owner> owners;
    Long id1;
    Long id2;
    String lastName1;
    String lastName2;
    Owner owner1;
    Owner owner2;

    Set<Pet> pets;
    Pet pet1;
    Pet pet2;

    Set<PetType> petTypes;
    PetType petType1;
    PetType petType2;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>(2);
        id1 = 1L;
        id2 = 2L;
        lastName1 = "Novo";
        lastName2 = "Jackson";
        owner1 = Owner.builder().id(id1).lastName(lastName1).build();
        owner2 = Owner.builder().id(id2).lastName(lastName2).build();
        owners.add(owner1);
        owners.add(owner2);
        petType1 = PetType.builder().id(1L).build();
        petType2 = PetType.builder().id(2L).build();
        pet1 = Pet.builder().id(1L).owner(owner1).petType(petType1).build();
        pet2 = Pet.builder().id(2L).owner(owner2).petType(petType2).build();
        mockMvc = MockMvcBuilders.standaloneSetup(testClass).build();
    }

    @AfterEach
    void tearDown() {
        owners = null;
        id1 = null;
        id2 = null;
        lastName1 = null;
        lastName2 = null;
        owner1 = null;
        owner2 = null;
        petType1 = null;
        petType2 = null;
        pet1 = null;
        pet2 = null;
        mockMvc = null;
    }

    @Test
    void initCreatePet() throws Exception {

        //given

        //when

        //then
        assertEquals(VIEW_PET_CREATION_FORM, testClass.initCreatePet(model));
        verify(model, times(1)).addAttribute(eq("pet"), isA(Pet.class));

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/{id}/pets/new", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(VIEW_PET_CREATION_FORM))
                .andExpect(MockMvcResultMatchers.model().attributeExists("pet"));

    }

    @Disabled
    @Test
    void processCreatePet() throws Exception {

        //given
        Pet expected = pet1;
        Map<String, String> target = new HashMap<>();
        String objectName = "Error";
        BindingResult bindingResult = new MapBindingResult(target, objectName);

        //when
        when(petService.save(pet1)).thenReturn(pet1);
        ArgumentCaptor<Pet> argumentCaptor = ArgumentCaptor.forClass(Pet.class);

        //then
        assertEquals("redirect:/owners/" + owner1.getId(), testClass.processCreatePet(pet1, bindingResult, model));
        verify(petService, times(1)).save(argumentCaptor.capture());
        Pet actual = argumentCaptor.getValue();
        assertEquals(expected, actual);

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/{id}/pets/new", 1).content(pet1.toString()).contentType(MediaType.TEXT_PLAIN))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(VIEW_PET_CREATION_FORM));

    }

    @Test
    void initUpdatePet() throws Exception {

        //given

        //when
        when(petService.findById(id1)).thenReturn(pet1);

        //then
        assertEquals(VIEW_PET_CREATION_FORM, testClass.initUpdatePet(String.valueOf(id1), model));
        verify(model, times(1)).addAttribute(eq("pet"), isA(Pet.class));

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/{id}/pets/{id}/edit", 1, 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(VIEW_PET_CREATION_FORM))
                .andExpect(MockMvcResultMatchers.model().attributeExists("pet"));

    }

    @Disabled
    @Test
    void processUpdatePet() throws Exception {

        //given
        Pet expected = pet1;
        Map<String, String> target = new HashMap<>();
        String objectName = "Error";
        BindingResult bindingResult = new MapBindingResult(target, objectName);

        //when
        when(petService.findById(id1)).thenReturn(pet1);
        when(petService.save(pet1)).thenReturn(pet1);
        when(model.getAttribute("owner")).thenReturn(owner1);
        ArgumentCaptor<Pet> argumentCaptor = ArgumentCaptor.forClass(Pet.class);

        //then
        assertEquals("redirect:/owners/" + pet1.getOwner().getId(), testClass.processUpdatePet(String.valueOf(id1), pet1, bindingResult, model));
        verify(petService, times(1)).save(argumentCaptor.capture());
        Pet actual = argumentCaptor.getValue();
        assertEquals(expected, actual);

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/{id}/pets/{id}/edit", 1, 1).content(pet1.toString()).contentType(MediaType.TEXT_PLAIN))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(VIEW_PET_CREATION_FORM));

    }
}
