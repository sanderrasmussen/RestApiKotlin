package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Bicycle;
import com.example.demo.repository.BikeRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/bicycle")
public class BikeController {
    private final BikeRepository bikeRepository;

    public BikeController(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    @GetMapping("{serialNumber}")
    public Bicycle getBicycleBySerialNumber(@PathVariable String serialNumber) {
        return bikeRepository.findBySerialNumber(serialNumber).orElseThrow(() -> new RuntimeException("bike not found"));
    }
    
}

