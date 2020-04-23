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
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetService petService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petService = petService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) {

        Owner owner = new Owner();
        owner.setFirstName("Leo");
        owner.setLastName("Novo");
        owner.setAddress("Canada Square");
        owner.setCity("London");
        owner.setTelephone("+11 987456320");
        ownerService.save(owner);

        Owner owner1 = new Owner();
        owner1.setFirstName("Intell");
        owner1.setLastName("Jackson");
        owner1.setAddress("Wall Street #2");
        owner1.setCity("New York");
        owner1.setTelephone("+1 326548970");
        ownerService.save(owner1);

        PetType dog = new PetType();
        dog.setName("Dog");
        petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        petTypeService.save(cat);

        Pet pet1 = new Pet();
        pet1.setName("Dog Bark");
        pet1.setPetType(dog);
        pet1.setBirthDate(LocalDate.of(2010, 10, 10));
        pet1.setOwner(owner1);
        petService.save(pet1);

        Pet pet2 = new Pet();
        pet2.setName("Cat Meow");
        pet2.setPetType(cat);
        pet2.setBirthDate(LocalDate.of(2018, 8, 12));
        pet2.setOwner(owner);
        petService.save(pet2);

        Pet pet3 = new Pet();
        pet3.setName("Jimmy Bark");
        pet3.setPetType(dog);
        pet3.setBirthDate(LocalDate.of(2020, 1, 10));
        pet3.setOwner(owner1);
        petService.save(pet3);

        Pet pet4 = new Pet();
        pet4.setName("Mimmy Meow");
        pet4.setPetType(cat);
        pet4.setBirthDate(LocalDate.of(2019, 3, 31));
        pet4.setOwner(owner);
        petService.save(pet4);

        Speciality speciality1 = new Speciality();
        speciality1.setDescription("Radiology");
        specialityService.save(speciality1);

        Speciality speciality2 = new Speciality();
        speciality2.setDescription("Surgeon");
        specialityService.save(speciality2);

        Speciality speciality3 = new Speciality();
        speciality3.setDescription("Dentist");
        specialityService.save(speciality3);

        Speciality speciality4 = new Speciality();
        speciality4.setDescription("Endoscopist");
        specialityService.save(speciality4);

        Vet vet = new Vet();
        vet.setFirstName("Log");
        vet.setLastName("Tech");
        HashSet<Speciality> specialities = new HashSet<>();
        specialities.add(speciality1);
        specialities.add(speciality2);
        vet.setSpecialities(specialities);
        vetService.save(vet);

        Vet vet1 = new Vet();
        vet1.setFirstName("Job");
        vet1.setLastName("Less");
        HashSet<Speciality> specialities1 = new HashSet<>();
        specialities1.add(speciality3);
        specialities1.add(speciality4);
        vet1.setSpecialities(specialities1);
        vetService.save(vet1);

        Visit visit1 = new Visit();
        visit1.setDate(LocalDate.of(2020, 04, 01));
        visit1.setPet(pet1);
        visitService.save(visit1);

        Visit visit2 = new Visit();
        visit2.setDate(LocalDate.of(2020, 04, 02));
        visit2.setPet(pet2);
        visitService.save(visit2);

        Visit visit3 = new Visit();
        visit3.setDate(LocalDate.of(2020, 04, 03));
        visit3.setPet(pet3);
        visitService.save(visit3);

        Visit visit4 = new Visit();
        visit4.setDate(LocalDate.of(2020, 04, 04));
        visit4.setPet(pet4);
        visitService.save(visit4);

        System.out.println("### " + ownerService.count() + " ###  " + ownerService.findAll() + " ###");
        System.out.println("### " + specialityService.count() + " ###  " + specialityService.findAll() + " ###");
        System.out.println("### " + vetService.count() + " ###  " + vetService.findAll() + " ###");
        System.out.println("### " + petService.count() + " ###  " + petService.findAll() + " ###");
        System.out.println("### " + petTypeService.count() + " ###  " + petTypeService.findAll() + " ###");
        System.out.println("### " + visitService.count() + " ###  " + visitService.findAll() + " ###");

    }
}
