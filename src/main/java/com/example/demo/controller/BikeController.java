package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.RegisterBicycleRequest;
import com.example.demo.model.Bicycle;
import com.example.demo.service.BikeService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/bicycle")
public class BikeController {
    private final BikeService bikeService;

    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @GetMapping("/{serialNumber}")
    public Bicycle getBicycleBySerialNumber(@PathVariable String serialNumber) {
        return bikeService.getBicycleBySerialNumber(serialNumber);
    }
    @PutMapping("/register")
    public Bicycle registerBicycle(@RequestBody RegisterBicycleRequest bicycleRequest){
        return bikeService.registerBike(bicycleRequest);
    }
    
}

