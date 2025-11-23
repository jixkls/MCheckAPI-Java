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

    @Column(nullable = false, length = 80)
    private String specialty;

    @Column(length = 30)
    private String phone;

    @Column(length = 120)
    private String email;
}
