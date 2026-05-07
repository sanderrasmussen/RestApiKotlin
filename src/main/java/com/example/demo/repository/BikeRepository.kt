package com.example.demo.repository

import com.example.demo.model.Bicycle
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface BikeRepository : JpaRepository<Bicycle, Long> {
    fun findBySerialNumber(serialNumber: String?): Bicycle?
}
