package com.example.demo.controller

import com.example.demo.model.Owner
import com.example.demo.repository.OwnerRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.function.Supplier

@RestController
@RequestMapping("/owner")
class OwnerController(private val ownerRepository: OwnerRepository) {
    @GetMapping("{email}")
    fun getOwnerByEmail(@PathVariable email: String?): Owner {
        return ownerRepository.findByEmail(email)
    }
}