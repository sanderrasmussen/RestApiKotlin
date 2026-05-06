package com.example.demo.controller;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Owner;
import com.example.demo.repository.OwnerRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/owner")
public class OwnerController {
    private final OwnerRepository ownerRepository;

    public OwnerController(OwnerRepository ownerRepository){
        this.ownerRepository = ownerRepository;
    }
    @GetMapping("{email}")
    public Owner getOwnerByEmail(@PathVariable String email){
        return ownerRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Owner not found"));
    }
}