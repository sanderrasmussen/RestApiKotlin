package com.example.demo.model

import jakarta.persistence.*

@Entity
class Owner {
    // =========================
    // GETTERS & SETTERS
    // =========================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    var firstName: String? = null
    var lastName: String? = null
    var email: String? = null

    @OneToMany(mappedBy = "owner")
    var bicycles: MutableList<Bicycle?> = ArrayList<Bicycle?>()

    fun addBicycle(bicycle: Bicycle) {
        bicycles.add(bicycle)
        bicycle.owner= this
    }

    fun removeBike(bicycle: Bicycle) {
        bicycles.remove(bicycle)
        bicycle.owner = null
    }
}