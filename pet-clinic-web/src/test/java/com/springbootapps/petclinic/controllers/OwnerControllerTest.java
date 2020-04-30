package com.springbootapps.petclinic.controllers;

import com.springbootapps.petclinic.model.Owner;
import com.springbootapps.petclinic.services.OwnerService;
import edu.emory.mathcs.backport.java.util.Arrays;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController testClass;

    @Mock
    Model model;

    Set<Owner> owners;
    Long id1;
    Long id2;
    String lastName1;
    String lastName2;
    Owner owner1;
    Owner owner2;

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
        mockMvc = null;
    }

    @Test
    void getList() throws Exception {

        //given
        Set<Owner> expected = owners;

        //when
        when(ownerService.findAll()).thenReturn(expected);
        ArgumentCaptor<Set<Owner>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //then
        assertEquals("owners/ownersList", testClass.getList(model));
        verify(ownerService, times(2)).findAll();
        verify(model, times(1)).addAttribute(eq("owners"), argumentCaptor.capture());
        Set<Owner> actual = argumentCaptor.getValue();
        assertEquals(expected.size(), actual.size());
        assertEquals(2, actual.size());
        assertEquals(expected, actual);

        mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/ownersList"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("owners"));

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/index"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/ownersList"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("owners"));

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/index.html"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/ownersList"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("owners"));
    }

    @Test
    void findOwnersByLastName_None() throws Exception {

        //given
        Map<String, String> target = new HashMap<>();
        String objectName = "Error";
        BindingResult bindingResult = new MapBindingResult(target, objectName);
        //when
        when(ownerService.findAllByLastNameLike("")).thenReturn(Arrays.asList(new Owner[]{}));
        //then
        assertEquals("redirect:/owners/find", testClass.processFindOwner(new Owner(), bindingResult, model));

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/findOwner").param("lastName", owner1.getLastName()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/owners/find"));
    }

    @Test
    void findOwnersByLastName_One() throws Exception {

        //given
        BindingResult bindingResult = null;
        //when
        when(ownerService.findAllByLastNameLike(owner1.getLastName())).thenReturn(Arrays.asList(new Owner[]{owner1}));
        //then
        assertEquals("redirect:/owners/" + owner1.getId(), testClass.processFindOwner(owner1, bindingResult, model));

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/findOwner").param("lastName", owner1.getLastName()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/owners/" + owner1.getId()));
    }

    @Test
    void findOwnersByLastName_Many() throws Exception {

        //given
        BindingResult bindingResult = null;
        //when
        when(ownerService.findAllByLastNameLike(owner1.getLastName())).thenReturn(Arrays.asList(new Owner[]{owner2, owner1}));
        //then
        assertEquals("owners/ownersList", testClass.processFindOwner(owner1, bindingResult, model));

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/findOwner").param("lastName", owner1.getLastName()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/ownersList"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("owners"));
    }

    @Test
    public void find() throws Exception {

        //when
        ArgumentCaptor<Owner> argumentCaptor = ArgumentCaptor.forClass(Owner.class);

        //this
        assertEquals("owners/findOwners", testClass.find(model));
        verify(model, times(1)).addAttribute(eq("owner"), argumentCaptor.capture());
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/find"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/findOwners"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("owner"));
    }

    @Test
    void getOwnerDetails() throws Exception {

        //given
        Owner expected = owner1;

        //when
        when(ownerService.findById(id1)).thenReturn(expected);
        ArgumentCaptor<Owner> argumentCaptor = ArgumentCaptor.forClass(Owner.class);

        //then
        assertEquals("owners/ownerDetails", testClass.getOwnerDetails(String.valueOf(id1), model));
        verify(ownerService, times(1)).findById(anyLong());
        verify(model, times(1)).addAttribute(eq("owner"), argumentCaptor.capture());
        Owner actual = argumentCaptor.getValue();
        assertEquals(expected, actual);

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/ownerDetails"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("owner"));
    }

    @Test
    void initCreateOwner() throws Exception {

        //given

        //when

        //then
        assertEquals("owners/createOrUpdateOwnerForm", testClass.initCreateOwner(model));
        verify(model, times(1)).addAttribute(eq("owner"), isA(Owner.class));

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("owner"));

    }

    @Disabled
    @Test
    void processCreateOwner() throws Exception {

        //given
        Owner owner = Owner.builder().build();
        Owner expected = owner1;
        Map<String, String> target = new HashMap<>();
        String objectName = "Error";
        BindingResult bindingResult = new MapBindingResult(target, objectName);

        //when
        when(ownerService.save(owner1)).thenReturn(owner1);
        ArgumentCaptor<Owner> argumentCaptor = ArgumentCaptor.forClass(Owner.class);

        //then
        assertEquals("redirect:/owners/" + owner1.getId(), testClass.processCreateOwner(owner1, bindingResult));
        verify(ownerService, times(1)).save(argumentCaptor.capture());
        Owner actual = argumentCaptor.getValue();
        assertEquals(expected, actual);

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/new").content(owner1.toString()).contentType(MediaType.TEXT_PLAIN))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/createOrUpdateOwnerForm"));

    }

    @Test
    void initUpdateOwner() throws Exception {

        //given

        //when

        //then
        assertEquals("redirect:/owners/new", testClass.initUpdateOwner("1", model));

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/{id}/edit", 1))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/owners/new"));

    }

    @Disabled
    @Test
    void processUpdateOwner() throws Exception {

        //given
        Owner owner = Owner.builder().build();
        Owner expected = owner1;
        Map<String, String> target = new HashMap<>();
        String objectName = "Error";
        BindingResult bindingResult = new MapBindingResult(target, objectName);

        //when
        when(ownerService.findById(1L)).thenReturn(owner1);
        when(ownerService.save(owner1)).thenReturn(owner1);
        ArgumentCaptor<Owner> argumentCaptor = ArgumentCaptor.forClass(Owner.class);

        //then
        assertEquals("redirect:/owners/1", testClass.processUpdateOwner("1", owner1, bindingResult));
        verify(ownerService, times(1)).findById(isA(Long.class));
        verify(ownerService, times(1)).save(argumentCaptor.capture());
        Owner actual = argumentCaptor.getValue();
        assertEquals(expected, actual);

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/{id}/edit", 1).content(owner1.toString()).contentType(MediaType.TEXT_PLAIN))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/createOrUpdateOwnerForm"));

    }
}