package com.springbootapps.petclinic.bootstrap;

import com.springbootapps.petclinic.model.Owner;
import com.springbootapps.petclinic.model.Vet;
import com.springbootapps.petclinic.services.OwnerService;
import com.springbootapps.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }


    @Override
    public void run(String... args) {
        Owner owner = new Owner();
        owner.setFirstName("Leo");
        owner.setLastName("Novo");

        ownerService.save(owner);

        Owner owner1 = new Owner();
        owner1.setFirstName("Intell");
        owner1.setLastName("Jackson");

        ownerService.save(owner1);

        System.out.println("###  " + ownerService.findAll() + " ###");

        Vet vet = new Vet();
        vet.setFirstName("Log");
        vet.setLastName("Tech");

        vetService.save(vet);

        Vet vet1 = new Vet();
        vet1.setFirstName("Job");
        vet1.setLastName("Less");

        vetService.save(vet1);

        System.out.println("###  " + vetService.findAll() + " ###");
    }
}
