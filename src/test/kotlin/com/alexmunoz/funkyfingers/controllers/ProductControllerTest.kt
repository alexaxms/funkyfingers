import com.alexmunoz.funkyfingers.controllers.ProductController
import com.alexmunoz.funkyfingers.entities.Product
import com.alexmunoz.funkyfingers.repositories.ProductRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.InjectMocks
import org.mockito.Mockito
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductControllerTest {
    @InjectMocks
    lateinit var productController: ProductController
    private val product1 = mockProduct(1)

    @BeforeAll
    fun setup(){
        val repository = Mockito.mock(ProductRepository::class.java)
        Mockito.`when`(repository.findAll()).thenReturn(listOf(product1))
        Mockito.`when`(repository.findById(1)).thenReturn(Optional.of(product1))
        productController = ProductController(repository)
    }

    private fun mockProduct(
            id: Int,
            name: String = "name$id"
    ): Product {
        return Product(id = id, name = name)
    }

    @Test
    fun getProducts() {
        assertNotNull(productController.getProducts())
    }

    @Test
    fun getProductById() {
        product1
        assertEquals(Optional.of(product1), productController.getProduct(1))
    }
}