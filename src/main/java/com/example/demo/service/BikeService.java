package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.RegisterBicycleRequest;
import com.example.demo.model.Bicycle;
import com.example.demo.model.Owner;
import com.example.demo.repository.BikeRepository;
import com.example.demo.repository.OwnerRepository;

import jakarta.transaction.Transactional;

@Service
public class BikeService {
    private final BikeRepository bikeRepository;
    private final OwnerRepository ownerRepository;

    public BikeService(BikeRepository bikeRepository, OwnerRepository ownerRepository) {
        this.bikeRepository = bikeRepository;
        this.ownerRepository = ownerRepository;

    }
    
    @Transactional
    public Bicycle getBicycleBySerialNumber(String serialNumber) {
        return bikeRepository.findBySerialNumber(serialNumber).orElseThrow(() -> new RuntimeException("bike not found"));
    }
    @Transactional
    public Bicycle SetOwner(String serialNumber, String userEmail ){ 
        Bicycle bicycle = bikeRepository.findBySerialNumber(serialNumber).orElseThrow(() -> new RuntimeException("Bike not found"));
        Owner newOwner = ownerRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("Owner not found"));

        Owner oldOwner = bicycle.getOwner();
        
        if (oldOwner != null){
            oldOwner.removeBike(bicycle);
        }

        if (newOwner!=null){
            newOwner.addBicycle(bicycle);
        }
        return bicycle;
    }
    @Transactional
    public Bicycle registerBike(RegisterBicycleRequest bicycleRequest){
        Bicycle bicycle = new Bicycle();
        Owner owner = ownerRepository.findByEmail(bicycleRequest.getOwnerEmail()).orElseThrow(() -> new RuntimeException("could not find owner with to register bike on"));
        bicycle.setOwner(owner); 
        bicycle.setBrand(bicycleRequest.getBrand());
        bicycle.setModel(bicycleRequest.getModel());
        bicycle.setSerialNumber(bicycleRequest.getSerialNumber());
        bikeRepository.save(bicycle);
        return bicycle;
    }
}