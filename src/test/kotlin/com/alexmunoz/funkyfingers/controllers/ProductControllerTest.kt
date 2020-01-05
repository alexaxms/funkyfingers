package com.alexmunoz.funkyfingers.controllers

import com.alexmunoz.funkyfingers.entities.Product
import com.alexmunoz.funkyfingers.exceptions.ProductNotFoundException
import com.alexmunoz.funkyfingers.services.ProductService
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.util.*

@WebMvcTest(ProductController::class)
class ProductControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var productService: ProductService

    @Test
    fun getProduct_ShouldReturnProduct() {
        given(productService.findById(1)).willReturn(Optional.of(Product(1, "title", "description")))
        mockMvc.perform(MockMvcRequestBuilders.get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").value("title"))
                .andExpect(jsonPath("description").value("description"))
    }

    @Test
    fun getProduct_notFound(){
        given(productService.findById(2)).willThrow(ProductNotFoundException())

        mockMvc.perform(MockMvcRequestBuilders.get("/products/2"))
                .andExpect(status().isNotFound)
    }

    @Test
    fun getAllProduct_ShouldReturnProducts() {
        given(productService.findAll()).willReturn(listOf(Product(1, "title", "description"),Product(2, "title", "description")))
        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':1,'title':'title','description':'description','imageUrl':null},{'id':2,'title':'title','description':'description','imageUrl':null}]"))
    }

    @Test
    fun createProduct_ShouldReturnProduct() {
        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"title\":\"title\",\"description\":\"description\",\"imageUrl\":null}")
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
