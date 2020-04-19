package com.springbootapps.petclinic.bootstrap;

import com.springbootapps.petclinic.model.Owner;
import com.springbootapps.petclinic.model.Pet;
import com.springbootapps.petclinic.model.PetType;
import com.springbootapps.petclinic.model.Vet;
import com.springbootapps.petclinic.services.OwnerService;
import com.springbootapps.petclinic.services.PetService;
import com.springbootapps.petclinic.services.PetTypeService;
import com.springbootapps.petclinic.services.VetService;
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

    public DataLoader(OwnerService ownerService, VetService vetService, PetService petService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) {
        PetType dog = new PetType();
        dog.setName("Dog");

        PetType cat = new PetType();
        cat.setName("Dog");

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
        owner.setPets(pets2);
        pet1.setOwner(owner1);

        Vet vet = new Vet();
        vet.setFirstName("Log");
        vet.setLastName("Tech");

        Vet vet1 = new Vet();
        vet1.setFirstName("Job");
        vet1.setLastName("Less");

        ownerService.save(owner);
        ownerService.save(owner1);

        vetService.save(vet);
        vetService.save(vet1);

        petTypeService.save(dog);
        petTypeService.save(cat);

        petService.save(pet1);
        petService.save(pet1);

        System.out.println("###  " + ownerService.findAll() + " ###");
        System.out.println("###  " + vetService.findAll() + " ###");
        System.out.println("###  " + petService.findAll() + " ###");
        System.out.println("###  " + petTypeService.findAll() + " ###");

    }
}
