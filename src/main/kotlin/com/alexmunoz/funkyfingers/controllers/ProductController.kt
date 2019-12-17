package com.alexmunoz.funkyfingers.controllers

import com.alexmunoz.funkyfingers.entities.Product
import com.alexmunoz.funkyfingers.exceptions.ProductNotFoundException
import com.alexmunoz.funkyfingers.services.ProductService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@CrossOrigin
@RestController
@RequestMapping("/products")
class ProductController(val productService: ProductService) {

    @GetMapping
    fun getProducts(): List<Product> = productService.findAll()

    @GetMapping("{productId}")
    fun getProduct(@PathVariable("productId") productId: Long): Optional<Product>? =
            productService.findById(productId)


    @PostMapping
    fun createProduct(@RequestBody product: Product): Product {
        productService.save(product)
        return product
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateProduct(product: Product) {
        productService.save(product)
    }

    @DeleteMapping("{productId}")
    fun deleteProduct(@PathVariable("productId") productId: Long) {
        productService.deleteById(productId)
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun productNotFoundHandler(ex: ProductNotFoundException){}
}