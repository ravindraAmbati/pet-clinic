package com.springbootapps.petclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "types")
public class PetType extends BaseEntity {

    @Builder
    public PetType(String name) {
        this.name = name;
    }

    @Column(name = "name")
    private String name;
}
