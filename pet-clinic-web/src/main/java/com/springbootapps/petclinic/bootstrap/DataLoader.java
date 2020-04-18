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
        owner.setId(1L);
        owner.setFirstName("Leo");
        owner.setLastName("Novo");

        ownerService.save(owner);

        System.out.println("### " + ownerService.findById(1L) + " ###");

        Vet vet = new Vet();
        vet.setId(2L);
        vet.setFirstName("Log");
        vet.setLastName("Tech");

        vetService.save(vet);

        System.out.println("### " + vetService.findById(2L) + " ###");
    }
}
