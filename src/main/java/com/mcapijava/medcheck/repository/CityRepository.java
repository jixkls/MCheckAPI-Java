package com.mcapijava.medcheck.repository;

import com.mcapijava.medcheck.models.City;
import org.springframework.data.jpa.repository.JpaRepository;

interface CityRepository extends JpaRepository<City, Long> {}
