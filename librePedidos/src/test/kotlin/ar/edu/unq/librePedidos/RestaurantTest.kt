import io.mockk.every
import io.mockk.mockk
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

import org.junit.Assert.*

class RestaurantTest {

    private val coordsMenu = mockk<Coordinates>()

    private val userAdmin = mockk<UserAdmin>()

    private val restaurant: Restaurant = Restaurant(1, "Mc Donals", "Solo Hamburguesas", "Muy Lejos", coordsMenu, mutableListOf(), mutableListOf(), userAdmin)

    private val menu1 = mockk<Menu>()
    private val menu2 = mockk<Menu>()

    private val prod1 = mockk<Product>()
    private val prod2 = mockk<Product>()
    private val prod3 = mockk<Product>()

    private val prod4 = mockk<Product>()
    private val prod5 = mockk<Product>()
    private val prod6 = mockk<Product>()

    @Before
    fun setUp() {
        every { menu1.name } returns "Hamburguesa con cheddar"
        every { menu1.code } returns 51447

        every { menu2.name } returns "Hamburguesa bacon smokehouse"
        every { menu2.code } returns 4151

        every { prod1.name } returns "Hamburguesa con cheddar"
        every { prod2.name } returns "Hamburguesa"
        every { prod3.name } returns "Hamburguesa bacon smokehouse"
        every { prod4.name } returns "Hamburguesa quemada"
        every { prod5.name } returns "Hamburguesa sin hamburguesa"
        every { prod6.name } returns "Color burguer"

        restaurant.addMenu(menu1)
        restaurant.addMenu(menu2)

        restaurant.addProduct(prod1)
        restaurant.addProduct(prod2)
        restaurant.addProduct(prod3)
        restaurant.addProduct(prod4)
        restaurant.addProduct(prod5)
        restaurant.addProduct(prod6)
    }

    @Test
    fun searchMenuByName() {
        assertEquals(mutableListOf(menu1), restaurant.searchMenuByName("Hamburguesa con cheddar"))
    }

    @Test
    fun searchMenuByCode() {
        assertEquals(menu2, restaurant.searchMenuByCode(4151))
    }

    @Test
    fun searchProductsByName() {
        assertEquals(mutableListOf(prod1, prod2, prod3, prod4, prod5), restaurant.searchProductsByName("Ham"))
    }

    @Test
    fun generateMenuCode(){
        assertEquals(0, restaurant.generateMenuCode())
        assertEquals(1, restaurant.generateMenuCode())
        assertEquals(2, restaurant.generateMenuCode())
        assertEquals(3, restaurant.generateMenuCode())
        assertEquals(4, restaurant.generateMenuCode())
    }

    @Test
    fun generateProductCode(){
        assertEquals(0, restaurant.generateProductCode())
        assertEquals(1, restaurant.generateProductCode())
        assertEquals(2, restaurant.generateProductCode())
        assertEquals(3, restaurant.generateProductCode())
        assertEquals(4, restaurant.generateProductCode())
    }


}