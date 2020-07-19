import io.mockk.every
import io.mockk.mockk
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import org.junit.Test
import org.junit.Assert.*

class MenuTest {

    private val menu : Menu = Menu(1)

    private val prod1 = mockk<Product>()

    private val prod2 = mockk<Product>()

    private val prod3 = mockk<Product>()

    private val prod4 = mockk<Product>()

    private val prod5 = mockk<Product>()

    @BeforeTest
    fun setUp() {

        every { prod1.price } returns 51

        every { prod2.price } returns 12

        every { prod3.price } returns 84

        every { prod4.price } returns 33

        every { prod5.price } returns 7

        menu.addProduct(prod1)
        menu.addProduct(prod2)
        menu.addProduct(prod3)
        menu.addProduct(prod4)
        menu.addProduct(prod5)
    }


    @Test
    fun getPriceDiscountTypeStatic() {
        menu.discountType = DiscountType.STATIC
        menu.valDiscount = 50

        val products = mutableListOf(prod1, prod2, prod3, prod4, prod5)

        assertEquals((products.sumBy { it.price } - 50).toDouble(), menu.getPrice())
    }

    @Test
    fun getPriceDiscountTypePercentege() {
        menu.discountType = DiscountType.PERCENTEGE
        menu.valDiscount = 50

        val products = mutableListOf(prod1, prod2, prod3, prod4, prod5)

        assertEquals((products.sumBy { it.price } * 0.5), menu.getPrice())
    }

}