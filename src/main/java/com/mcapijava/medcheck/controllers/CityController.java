package com.mcapijava.medcheck.controllers;

import com.mcapijava.medcheck.dto.CityRequest;
import com.mcapijava.medcheck.dto.CityResponse;
import com.mcapijava.medcheck.models.City;
import com.mcapijava.medcheck.repository.CityRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cities")
public class CityController {

    private final CityRepository cityRepo;

    public CityController(CityRepository cityRepo) {
        this.cityRepo = cityRepo;
    }

    @GetMapping
    public List<CityResponse> getCities() {
        return cityRepo.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public CityResponse get(@PathVariable Long id) {
        var city = cityRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));
        return toResponse(city);
    }

    @PostMapping
    public CityResponse create(@RequestBody @Valid CityRequest request) {
        var city = new City();
        city.setName(request.name());
        city.setState(request.state());

        var saved = cityRepo.save(city);
        return toResponse(saved);
    }

    @PutMapping("/{id}")
    public CityResponse update(
            @PathVariable Long id,
            @RequestBody @Valid CityRequest request
    ) {
        var city = cityRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));

        city.setName(request.name());
        city.setState(request.state());

        var updated = cityRepo.save(city);
        return toResponse(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if (!cityRepo.existsById(id)) {
            throw new RuntimeException("Cidade não encontrada");
        }
        cityRepo.deleteById(id);
    }

    private CityResponse toResponse(City city) {
        return new CityResponse(
                city.getId(),
                city.getName(),
                city.getState()
        );
    }
}
