package com.example.demo.controller

import com.example.demo.dto.RegisterBicycleRequest
import com.example.demo.model.Bicycle
import com.example.demo.service.BikeService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/bicycle")
class BikeController(private val bikeService: BikeService) {
    @GetMapping("/{serialNumber}")
    fun getBicycleBySerialNumber(@PathVariable serialNumber: String?): Bicycle? {
        return bikeService.getBicycleBySerialNumber(serialNumber)
    }

    @PutMapping("/register")
    fun registerBicycle(@RequestBody bicycleRequest: RegisterBicycleRequest): Bicycle? {
        return bikeService.registerBike(bicycleRequest)
    }
}

