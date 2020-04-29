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

        Owner owner1 = Owner.builder().firstName("Leo").lastName("Novo").address("Small Street #67A").city("Boston").telephone("+11 987456320").build();
        Owner owner2 = Owner.builder().firstName("Intell").lastName("Jack").address("Wall Street #24").city("New Jersy").telephone("+1 9847561230").build();
        Owner owner3 = Owner.builder().firstName("Leonerd").lastName("Novortis").address("Same Street #43").city("Buffalo").telephone("+11 987456320").build();
        Owner owner4 = Owner.builder().firstName("IntellJ").lastName("Jackson").address("Big Street #3").city("New York").telephone("+1 9632548710").build();


        PetType dog = PetType.builder().name("Dog").build();
        PetType cat = PetType.builder().name("Cat").build();

        Pet pet1 = Pet.builder().name("Dog Bark").petType(dog).birthDate(LocalDate.of(2017, 9, 1)).owner(owner1).build();
        Pet pet2 = Pet.builder().name("Cat Meow").petType(cat).birthDate(LocalDate.of(2018, 10, 2)).owner(owner2).build();
        Pet pet3 = Pet.builder().name("Jimmy Bark").petType(dog).birthDate(LocalDate.of(2019, 11, 3)).owner(owner3).build();
        Pet pet4 = Pet.builder().name("Mimmy Meow").petType(cat).birthDate(LocalDate.of(2020, 12, 4)).owner(owner4).build();
        Pet pet5 = Pet.builder().name("No Bark").petType(dog).birthDate(LocalDate.of(2017, 1, 5)).owner(owner1).build();
        Pet pet6 = Pet.builder().name("No Meow").petType(cat).birthDate(LocalDate.of(2018, 2, 6)).owner(owner2).build();
        Pet pet7 = Pet.builder().name("Less Bark").petType(dog).birthDate(LocalDate.of(2019, 3, 7)).owner(owner3).build();
        Pet pet8 = Pet.builder().name("Less Meow").petType(cat).birthDate(LocalDate.of(2020, 4, 8)).owner(owner4).build();

        Speciality radiology = Speciality.builder().description("Radiology").build();
        Speciality surgeon = Speciality.builder().description("Surgeon").build();
        Speciality dentist = Speciality.builder().description("Dentist").build();
        Speciality endoscopist = Speciality.builder().description("Endoscopist").build();
        Speciality general = Speciality.builder().description("General").build();

        HashSet<Speciality> specialities1 = new HashSet<>();
        specialities1.add(dentist);
        specialities1.add(endoscopist);
        specialities1.add(general);

        Vet vet1 = Vet.builder().firstName("Log").lastName("Tech").specialities(specialities1).build();
        vetService.save(vet1);

        HashSet<Speciality> specialities2 = new HashSet<>();
        specialities2.add(radiology);
        specialities2.add(surgeon);
        specialities2.add(general);

        Vet vet2 = Vet.builder().firstName("Job").lastName("Less").specialities(specialities2).build();
        vetService.save(vet2);


        Visit visit1 = Visit.builder().date(LocalDate.of(2020, 4, 1)).pet(pet1).build();
        visitService.save(visit1);

        Visit visit2 = Visit.builder().date(LocalDate.of(2020, 3, 2)).pet(pet2).build();
        visitService.save(visit2);

        Visit visit3 = Visit.builder().date(LocalDate.of(2020, 2, 3)).pet(pet3).build();
        visitService.save(visit3);

        Visit visit4 = Visit.builder().date(LocalDate.of(2020, 1, 4)).pet(pet4).build();
        visitService.save(visit4);

        Visit visit5 = Visit.builder().date(LocalDate.of(2020, 1, 1)).pet(pet5).build();
        visitService.save(visit5);

        Visit visit6 = Visit.builder().date(LocalDate.of(2020, 2, 2)).pet(pet6).build();
        visitService.save(visit6);

        Visit visit7 = Visit.builder().date(LocalDate.of(2020, 3, 3)).pet(pet7).build();
        visitService.save(visit7);

        Visit visit8 = Visit.builder().date(LocalDate.of(2020, 4, 4)).pet(pet8).build();
        visitService.save(visit8);

        log.info(ownerService.count() + "");
        ownerService.findAll().forEach(owner -> log.info(owner.toString()));
        log.info(petTypeService.count() + "");
        petTypeService.findAll().forEach(petType -> log.info(petType.toString()));
        log.info(petService.count() + "");
        petService.findAll().forEach(pet -> log.info(pet.toString()));
        log.info(specialityService.count() + "");
        specialityService.findAll().forEach(speciality -> log.info(speciality.toString()));
        log.info(vetService.count() + "");
        vetService.findAll().forEach(vet -> log.info(vet.toString()));
        log.info(visitService.count() + "");
        visitService.findAll().forEach(visit -> log.info(visit.toString()));
    }
}
