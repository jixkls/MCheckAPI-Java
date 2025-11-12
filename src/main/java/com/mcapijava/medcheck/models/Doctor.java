package com.mcapijava.medcheck.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "doctors", uniqueConstraints = @UniqueConstraint(columnNames = "crm"))
public class Doctor {

    @Id
    private UUID id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, length = 20)
    private String crm;

    @ManyToMany
    @JoinTable(name = "doctor_specialties",
        joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private Set<Specialty> specialties = new LinkedHashSet<>(); // Tem que ser Set pra não repetir a especialidade

}
