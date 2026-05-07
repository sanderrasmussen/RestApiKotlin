package com.example.demo.repository

import com.example.demo.model.Owner
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface OwnerRepository : JpaRepository<Owner, Long>{
    fun findByEmail(email: String?): Owner
}
