package com.alexmunoz.funkyfingers.entities

import javax.persistence.*

@Entity
data class Product(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        val title: String,
        val description: String,
        val imageUrl: String? = null
)
