package com.springbootapps.petclinic.bootstrap;

import com.springbootapps.petclinic.model.*;
import com.springbootapps.petclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetService petService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetService petService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petService = petService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) {
        PetType dog = new PetType();
        dog.setName("Dog");

        PetType cat = new PetType();
        cat.setName("Cat");

        Pet pet1 = new Pet();

        pet1.setPetType(dog);
        pet1.setBirthDate(LocalDate.of(2010, 10, 10));

        Pet pet2 = new Pet();

        pet2.setPetType(cat);
        pet2.setBirthDate(LocalDate.of(2018, 8, 12));

        Owner owner = new Owner();
        owner.setFirstName("Leo");
        owner.setLastName("Novo");
        owner.setAddress("Canada Square");
        owner.setCity("London");
        owner.setTelephone("+11 987456320");
        HashSet<Pet> pets1 = new HashSet<>();
        pets1.add(pet1);
        owner.setPets(pets1);
        pet2.setOwner(owner);

        Owner owner1 = new Owner();
        owner1.setFirstName("Intell");
        owner1.setLastName("Jackson");
        owner1.setAddress("Wall Street #2");
        owner.setCity("New York");
        owner1.setTelephone("+1 326548970");
        HashSet<Pet> pets2 = new HashSet<>();
        pets2.add(pet2);
        owner1.setPets(pets2);
        pet1.setOwner(owner1);

        Speciality speciality = new Speciality();
        speciality.setDescription("radiology");

        Speciality speciality1 = new Speciality();
        speciality1.setDescription("surgeon");

        Speciality speciality2 = new Speciality();
        speciality2.setDescription("densist");

        Vet vet = new Vet();
        vet.setFirstName("Log");
        vet.setLastName("Tech");
        HashSet<Speciality> specialities = new HashSet<>();
        specialities.add(speciality1);
        specialities.add(speciality2);
        vet.setSpecialities(specialities);

        Vet vet1 = new Vet();
        vet1.setFirstName("Job");
        vet1.setLastName("Less");
        HashSet<Speciality> specialities1 = new HashSet<>();
        specialities1.add(speciality);
        vet1.setSpecialities(specialities1);

        ownerService.save(owner);
        ownerService.save(owner1);

        vetService.save(vet);
        vetService.save(vet1);

        specialityService.save(speciality);
        specialityService.save(speciality1);

        petTypeService.save(dog);
        petTypeService.save(cat);

        petService.save(pet1);
        petService.save(pet2);

        System.out.println("###  " + ownerService.findAll() + " ###");
        System.out.println("###  " + specialityService.findAll() + " ###");
        System.out.println("###  " + vetService.findAll() + " ###");
        System.out.println("###  " + petService.findAll() + " ###");
        System.out.println("###  " + petTypeService.findAll() + " ###");

    }
}
