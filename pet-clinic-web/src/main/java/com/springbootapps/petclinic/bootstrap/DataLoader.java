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
        PetType dog = new PetType();
        dog.setName("Dog");

        PetType cat = new PetType();
        cat.setName("Cat");

        Pet pet1 = new Pet();

        pet1.setName("Dog Bark");
        pet1.setPetType(dog);
        pet1.setBirthDate(LocalDate.of(2010, 10, 10));

        Pet pet2 = new Pet();

        pet2.setName("Cat Meow");
        pet2.setPetType(cat);
        pet2.setBirthDate(LocalDate.of(2018, 8, 12));

        Pet pet3 = new Pet();

        pet3.setName("Jimmy Bark");
        pet3.setPetType(dog);
        pet3.setBirthDate(LocalDate.of(2020, 1, 10));

        Pet pet4 = new Pet();

        pet4.setName("Mimmy Meow");
        pet4.setPetType(cat);
        pet4.setBirthDate(LocalDate.of(2019, 3, 31));

        Owner owner = new Owner();
        owner.setFirstName("Leo");
        owner.setLastName("Novo");
        owner.setAddress("Canada Square");
        owner.setCity("London");
        owner.setTelephone("+11 987456320");
        HashSet<Pet> pets1 = new HashSet<>();
        pets1.add(pet1);
        pets1.add(pet4);
        owner.setPets(pets1);

        Owner owner1 = new Owner();
        owner1.setFirstName("Intell");
        owner1.setLastName("Jackson");
        owner1.setAddress("Wall Street #2");
        owner1.setCity("New York");
        owner1.setTelephone("+1 326548970");
        HashSet<Pet> pets2 = new HashSet<>();
        pets2.add(pet2);
        pets2.add(pet3);
        owner1.setPets(pets2);

        Speciality speciality1 = new Speciality();
        speciality1.setDescription("Radiology");

        Speciality speciality2 = new Speciality();
        speciality2.setDescription("Surgeon");

        Speciality speciality3 = new Speciality();
        speciality3.setDescription("Dentist");

        Speciality speciality4 = new Speciality();
        speciality4.setDescription("Endoscopist");

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
        specialities1.add(speciality3);
        specialities1.add(speciality4);
        vet1.setSpecialities(specialities1);


        Visit visit1 = new Visit();
        visit1.setDate(LocalDate.of(2020, 04, 01));
        visit1.setPet(pet1);

        Visit visit2 = new Visit();
        visit2.setDate(LocalDate.of(2020, 04, 02));
        visit2.setPet(pet2);

        Visit visit3 = new Visit();
        visit3.setDate(LocalDate.of(2020, 04, 03));
        visit3.setPet(pet3);

        Visit visit4 = new Visit();
        visit4.setDate(LocalDate.of(2020, 04, 04));
        visit4.setPet(pet4);

        // Specialists will be get saved along with Vets
        vetService.save(vet);
        vetService.save(vet1);

        // Owner will be get saved along with Visit (Pet and PetTypes will be get saved along with Owners)
        visitService.save(visit1);
        visitService.save(visit2);
        visitService.save(visit3);
        visitService.save(visit4);


        System.out.println("### " + ownerService.count() + " ###  " + ownerService.findAll() + " ###");
        System.out.println("### " + specialityService.count() + " ###  " + specialityService.findAll() + " ###");
        System.out.println("### " + vetService.count() + " ###  " + vetService.findAll() + " ###");
        System.out.println("### " + petService.count() + " ###  " + petService.findAll() + " ###");
        System.out.println("### " + petTypeService.count() + " ###  " + petTypeService.findAll() + " ###");
        System.out.println("### " + visitService.count() + " ###  " + visitService.findAll() + " ###");

    }
}
