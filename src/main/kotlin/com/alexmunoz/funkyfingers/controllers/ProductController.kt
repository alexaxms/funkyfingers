package com.alexmunoz.funkyfingers.controllers

import com.alexmunoz.funkyfingers.entities.Product
import com.alexmunoz.funkyfingers.repositories.ProductRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@CrossOrigin(origins = ["http://localhost:3000"], maxAge = 3600)
@RestController
@RequestMapping("/products")
class ProductController(val productRepository: ProductRepository) {

    @GetMapping
    fun getProducts(): List<Product> = productRepository.findAll()

    @RequestMapping(path = [("/{productId}")], method = [(RequestMethod.GET)])
    fun getProduct(@PathVariable("productId") productId: Long): Optional<Product>? =
         productRepository.findById(productId)


    @PostMapping
    fun createProduct(@RequestBody product: Product): Product {
        productRepository.save(product)
        return product
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateProduct(product: Product) {
        productRepository.save(product)
    }

    @RequestMapping(path = [("/{productId}")], method = [(RequestMethod.DELETE)])
    fun deleteProduct(@PathVariable("productId") productId: Long) {
        productRepository.deleteById(productId)
    }
}