package com.alexmunoz.funkyfingers.repositories

import com.alexmunoz.funkyfingers.entities.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long>
