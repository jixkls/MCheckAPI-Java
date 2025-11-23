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

    @Column(nullable = false, length = 20)
    private String city;

    @ManyToMany
    @JoinColumn(name = "specialty_id", nullable = false)
    private Specialty specialty;
}
