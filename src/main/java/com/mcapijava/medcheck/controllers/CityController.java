package com.mcapijava.medcheck.controllers;
import com.mcapijava.medcheck.models.City;
import com.mcapijava.medcheck.repository.CityRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Cidades")
public class CityController {
    private final CityRepository cRepo;
    public CityController(CityRepository cRepo) {
        this.cRepo = cRepo;
    }

    @GetMapping
    public List<City> list() {
        return cRepo.findAll();
    }
}
