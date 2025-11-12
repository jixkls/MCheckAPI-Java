package com.mcapijava.medcheck.controllers;
import com.mcapijava.medcheck.models.Specialty;
import com.mcapijava.medcheck.repository.SpecialtyRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/especialidades")
public class SpecialtyController {

    private final SpecialtyRepository sRepo;
    public SpecialtyController(SpecialtyRepository sRepo) {
        this.sRepo = sRepo;
    }

    @GetMapping
    public List<Specialty> list() {
        return sRepo.findAll();
    }
}
