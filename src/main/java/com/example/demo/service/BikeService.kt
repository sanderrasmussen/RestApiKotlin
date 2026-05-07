package com.example.demo.service

import com.example.demo.dto.RegisterBicycleRequest
import com.example.demo.model.Bicycle
import com.example.demo.repository.BikeRepository
import com.example.demo.repository.OwnerRepository
import jakarta.transaction.Transactional
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service
import java.util.function.Supplier

@Service
open class BikeService(private val bikeRepository: BikeRepository, private val ownerRepository: OwnerRepository) {
    @Transactional
    fun getBicycleBySerialNumber(serialNumber: String?): Bicycle {
        return bikeRepository.findBySerialNumber(serialNumber)
                ?: throw RuntimeException("bike not found")
    }

    @Transactional
    fun SetOwner(serialNumber: String?, userEmail: String?): Bicycle {
        val bicycle = bikeRepository.findBySerialNumber(serialNumber) ?: throw RuntimeException("bike not found")
        val newOwner = ownerRepository.findByEmail(userEmail)
        val oldOwner = bicycle.owner ?: throw RuntimeException("owner not found")
        oldOwner.removeBike(bicycle)
        newOwner.addBicycle(bicycle)
        return bicycle
    }

    @Transactional
    fun registerBike(bicycleRequest: RegisterBicycleRequest): Bicycle {
        val bicycle = Bicycle()
        val owner = ownerRepository.findByEmail(bicycleRequest.ownerEmail)
        bicycle.owner = owner
        bicycle.brand = bicycleRequest.brand
        bicycle.model = bicycleRequest.model
        bicycle.serialNumber = bicycleRequest.serialNumber
        bikeRepository.save(bicycle)
        return bicycle
    }
}