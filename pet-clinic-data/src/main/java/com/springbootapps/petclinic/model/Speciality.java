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
@Table(name = "specialities")
public class Speciality extends BaseEntity {

    @Builder
    public Speciality(String description) {
        this.description = description;
    }

    @Column(name = "description")
    private String description;
}
