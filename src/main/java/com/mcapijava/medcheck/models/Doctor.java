package com.mcapijava.medcheck.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "doctors", uniqueConstraints = @UniqueConstraint(columnNames = "crm"))
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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

    @ManyToMany
    @JoinTable(name = "doctor_cities",
        joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "city_id"))
    private Set<City> cities = new LinkedHashSet<>();
}
