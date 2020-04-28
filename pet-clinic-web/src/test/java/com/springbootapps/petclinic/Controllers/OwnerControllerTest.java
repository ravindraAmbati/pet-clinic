package com.springbootapps.petclinic.Controllers;

import com.springbootapps.petclinic.model.Owner;
import com.springbootapps.petclinic.services.OwnerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
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
                .andExpect(MockMvcResultMatchers.view().name("owners/ownersList"));

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/index"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/ownersList"));

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/index.html"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/ownersList"));
    }

    @Disabled
    @Test
    void findOwnersByLastName_NotNull() throws Exception {

        //given
        Owner expected = owner1;

        //when
        when(ownerService.findByLastName(expected.getLastName())).thenReturn(expected);
        ArgumentCaptor<Owner> argumentCaptor = ArgumentCaptor.forClass(Owner.class);

        //then
        assertEquals("NotImplYet", testClass.findOwnersByLastName(expected.getLastName(), model));
        verify(model, times(1)).addAttribute(eq("owners"), argumentCaptor.capture());

        mockMvc.perform(MockMvcRequestBuilders.get("/findOwner").param("lastName", lastName1)).andReturn().getResponse();
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.view().name("NotImplYet"));
    }

    @Disabled
    @Test
    void findOwnersByLastName_Null() throws Exception {

        //given

        //when

        //then
        assertEquals("redirect: owners/", testClass.findOwnersByLastName(null, model));

        mockMvc.perform(MockMvcRequestBuilders.get("/findOwner?lastName="))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("owners/ownersList"));
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
                .andExpect(MockMvcResultMatchers.view().name("owners/findOwners"));
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

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/" + 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/ownerDetails"));
    }
}