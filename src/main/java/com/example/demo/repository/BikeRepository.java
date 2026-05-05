package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Bicycle;

public interface BikeRepository extends JpaRepository<Bicycle, Long> {
    Optional<Bicycle> findBySerialNumber(String serialNumber);
}
