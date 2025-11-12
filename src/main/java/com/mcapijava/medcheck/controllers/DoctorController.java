package com.mcapijava.medcheck.controllers;


import com.mcapijava.medcheck.dto.DoctorResponse;
import com.mcapijava.medcheck.models.Doctor;
import com.mcapijava.medcheck.models.Specialty;
import com.mcapijava.medcheck.repository.DoctorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {
    private final DoctorRepository doctorRepo;

    public DoctorController(DoctorRepository doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    @GetMapping
    public Page<DoctorResponse> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        if (size > 50) size = 50;

        var pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<Doctor> doctors = doctorRepo.findAll(pageable);
        return doctors.map(this::toResponse);
    }

    @GetMapping("/{id}")
    public DoctorResponse get(@PathVariable UUID id) {
        var doc = doctorRepo.findById(id).orElseThrow(() -> new RuntimeException("Doutor não encontrado"));
        return toResponse(doc);
    }

    private DoctorResponse toResponse(Doctor doctor) {
        var specialtiesNames = doctor.getSpecialties().stream().map(Specialty::getName).toList();
        var cityNames = doctor.getCities().stream().map(city -> city.getName() + "/" + city.getState()).toList();

        return new DoctorResponse(
                doctor.getId(),
                doctor.getName(),
                doctor.getCrm(),
                specialtiesNames,
                cityNames
        );
    }

}
