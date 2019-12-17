package com.alexmunoz.funkyfingers.services

import com.alexmunoz.funkyfingers.entities.Product
import com.alexmunoz.funkyfingers.repositories.ProductRepository
import org.springframework.stereotype.Component
import java.util.*


@Component
class ProductService(val productRepository: ProductRepository){
    fun findAll(): List<Product> {
        return productRepository.findAll()
    }

    fun findById(id: Long): Optional<Product>?{
        return productRepository.findById(id)
    }

    fun save(product: Product) {
        productRepository.save(product)
    }

    fun deleteById(productId: Long) {
        productRepository.deleteById(productId)
    }
}
