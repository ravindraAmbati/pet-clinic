package com.springbootapps.petclinic.bootstrap;

import com.springbootapps.petclinic.model.*;
import com.springbootapps.petclinic.services.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;

@Slf4j
@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetService petService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    @Transactional
    @Override
    public void run(String... args) {

        Owner owner = Owner.builder().firstName("Leo").lastName("Novo").address("Canada Square").city("London").telephone("+11 987456320").build();
        ownerService.save(owner);

        Owner owner1 = Owner.builder().firstName("Intell").lastName("Jackson").address("Wall Street #2").city("New York").telephone("+1 326548970").build();
        ownerService.save(owner1);

        PetType dog = PetType.builder().name("Dog").build();
        petTypeService.save(dog);

        PetType cat = PetType.builder().name("Cat").build();
        petTypeService.save(cat);

        Pet pet1 = Pet.builder().name("Dog Bark").petType(dog).birthDate(LocalDate.of(2010, 10, 10)).owner(owner1).build();
        petService.save(pet1);

        Pet pet2 = Pet.builder().name("Cat Meow").petType(cat).birthDate(LocalDate.of(2018, 8, 12)).owner(owner).build();
        petService.save(pet2);

        Pet pet3 = Pet.builder().name("Jimmy Bark").petType(dog).birthDate(LocalDate.of(2020, 1, 10)).owner(owner1).build();
        petService.save(pet3);

        Pet pet4 = Pet.builder().name("Mimmy Meow").petType(cat).birthDate(LocalDate.of(2019, 3, 31)).owner(owner).build();
        petService.save(pet4);

        Speciality speciality1 = Speciality.builder().description("Radiology").build();
        specialityService.save(speciality1);

        Speciality speciality2 = Speciality.builder().description("Surgeon").build();
        specialityService.save(speciality2);

        Speciality speciality3 = Speciality.builder().description("Dentist").build();
        specialityService.save(speciality3);

        Speciality speciality4 = Speciality.builder().description("Endoscopist").build();
        specialityService.save(speciality4);

        HashSet<Speciality> specialities = new HashSet<>();
        specialities.add(speciality1);
        specialities.add(speciality2);

        Vet vet = Vet.builder().firstName("Log").lastName("Tech").specialities(specialities).build();
        vetService.save(vet);

        HashSet<Speciality> specialities1 = new HashSet<>();
        specialities1.add(speciality3);
        specialities1.add(speciality4);

        Vet vet1 = Vet.builder().firstName("Job").lastName("Less").specialities(specialities1).build();
        vetService.save(vet1);


        Visit visit1 = Visit.builder().date(LocalDate.of(2020, 4, 1)).pet(pet1).build();
        visitService.save(visit1);

        Visit visit2 = Visit.builder().date(LocalDate.of(2020, 4, 2)).pet(pet2).build();
        visitService.save(visit2);

        Visit visit3 = Visit.builder().date(LocalDate.of(2020, 4, 3)).pet(pet3).build();
        visitService.save(visit3);

        Visit visit4 = Visit.builder().date(LocalDate.of(2020, 4, 4)).pet(pet3).build();
        visitService.save(visit4);

        log.info(ownerService.count() + "");
        ownerService.findAll().forEach(owner2 -> log.info(owner2.toString()));
        log.info(petTypeService.count() + "");
        petTypeService.findAll().forEach(petType -> log.info(petType.toString()));
        log.info(petService.count() + "");
        petService.findAll().forEach(pet -> log.info(pet.toString()));
        log.info(specialityService.count() + "");
        specialityService.findAll().forEach(speciality -> log.info(speciality.toString()));
        log.info(vetService.count() + "");
        vetService.findAll().forEach(vet2 -> log.info(vet2.toString()));
        log.info(visitService.count() + "");
        visitService.findAll().forEach(visit -> log.info(visit.toString()));
    }
}
