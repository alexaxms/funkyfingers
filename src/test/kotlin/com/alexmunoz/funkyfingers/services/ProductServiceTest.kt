package com.alexmunoz.funkyfingers.services

import com.alexmunoz.funkyfingers.entities.Product
import com.alexmunoz.funkyfingers.repositories.ProductRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class ProductServiceTest {
    @Mock
    lateinit var productRepository: ProductRepository


    @Test
    fun findById_ShouldReturnProduct() {
        given(productRepository.findById(1)).willReturn(Optional.of(Product(1, "title", "description")))
        val productService = ProductService(productRepository)
        val product = productService.findById(1)

        assertThat(product?.get()?.title).isEqualTo("title")
        assertThat(product?.get()?.description).isEqualTo("description")
    }

    @Test
    fun findAll_ShouldReturnProducts() {
        given(productRepository.findAll()).willReturn(listOf(Product(1, "title", "description"),Product(2, "title2", "description")))
        val productService = ProductService(productRepository)
        val products = productService.findAll()

        assertThat(products[0].title).isEqualTo("title")
        assertThat(products[1].title).isEqualTo("title2")
    }
}