package com.mcapijava.medcheck.controllers;
import com.mcapijava.medcheck.dto.SpecialtyRequest;
import com.mcapijava.medcheck.dto.SpecialtyResponse;
import com.mcapijava.medcheck.models.Specialty;
import com.mcapijava.medcheck.repository.SpecialtyRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/specialties")
public class SpecialtyController {

    private final SpecialtyRepository specialtyRepo;

    public SpecialtyController(SpecialtyRepository specialtyRepo) {
        this.specialtyRepo = specialtyRepo;
    }

    @GetMapping
    public List<SpecialtyResponse> getSpecialties() {
        return specialtyRepo.findAll().stream().map(this::toResponse).toList();
    }

    @GetMapping("/{id}")
    public SpecialtyResponse get(@PathVariable Long id) {
        var specialty = specialtyRepo.findById(id).orElseThrow(() -> new RuntimeException("Especialidade não encontrada"));
        return toResponse(specialty);
    }

    @PostMapping
    public SpecialtyResponse create(@RequestBody @Valid SpecialtyRequest request) {
        var specialty = new Specialty();
        specialty.setName(request.name());

        var saved = specialtyRepo.save(specialty);
        return toResponse(saved);
    }

    @PutMapping("/{id}")
    public SpecialtyResponse update(@PathVariable Long id, @RequestBody @Valid SpecialtyRequest request) {
        var specialty = specialtyRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Especialidade não encontrada"));

        specialty.setName(request.name());

        var updated = specialtyRepo.save(specialty);
        return toResponse(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if (!specialtyRepo.existsById(id)) {
            throw new RuntimeException("Especialidade não encontrada");
        }
        specialtyRepo.deleteById(id);
    }

    private SpecialtyResponse toResponse(Specialty specialty) {
        return new SpecialtyResponse(
                specialty.getId(),
                specialty.getName()
        );
    }
}
