package com.example.demo.model

import jakarta.persistence.*

@Entity
class Bicycle {
    // =========================
    // GETTERS & SETTERS
    // =========================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    var serialNumber: String? = null
    var brand: String? = null
    var model: String? = null

    @ManyToOne
    @JoinColumn(name = "owner_id")
    var owner: Owner? = null
}