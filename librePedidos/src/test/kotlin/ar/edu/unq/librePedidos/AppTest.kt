import Exceptions.InvalidPayMethodException
import Exceptions.MenuEmptyException
import Exceptions.RestaurantOutOfRangeException
import io.mockk.every
import io.mockk.mockk
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import org.junit.Test as test

class AppTest {

    private val user1 = mockk<User>()

    private val coordsUser = mockk<Coordinates>()

    private val coordsRest1 = mockk<Coordinates>()

    private val coordsRest2 = mockk<Coordinates>()

    private val destination = mockk<Coordinates>()

    private val rest1 = mockk<Restaurant>()
    private val rest2 = mockk<Restaurant>()
    private val rest3 = mockk<Restaurant>()

    private val prod1 = mockk<Product>()
    private val prod2 = mockk<Product>()
    private val prod3 = mockk<Product>()

    private val menu1 = mockk<Menu>()
    private val menu2 = mockk<Menu>()
    private val menu3 = mockk<Menu>()

    private val userManager = mockk<UserManager>()

    private val app = App(userManager)

    @BeforeTest
    fun setUp() {

        every { user1.coordinates } returns coordsUser

        every { coordsUser.latitude } returns 5.11871
        every { coordsUser.longitude } returns 7.13271

        every { coordsRest1.latitude } returns 8.17635
        every { coordsRest1.longitude } returns 11.85415

        every { coordsRest2.latitude } returns 874.14844
        every { coordsRest2.longitude } returns 991.15181

        every { menu1.code } returns 15
        every { menu2.code } returns 10
        every { menu3.code } returns 20
        every { menu1.products } returns mutableListOf(prod1)
        every { menu2.products } returns mutableListOf(prod1, prod2)
        every { menu3.products } returns mutableListOf(prod1, prod2, prod3)

        every { prod1.name } returns "Pollo"
        every { prod1.price } returns 15
        every { prod1.category } returns Category.MAIN_DISH
        every { prod1.code } returns 157

        every { prod2.name } returns "Coca"
        every { prod2.price } returns 7
        every { prod2.category } returns Category.SOFT_DRINK
        every { prod2.code } returns 6415

        every { prod3.name } returns "Pizza"
        every { prod3.price } returns 41
        every { prod3.category } returns Category.MAIN_DISH
        every { prod3.code } returns 52

        every { rest1.payMethods.contains(PayMethods.CASH) } returns true
        every { rest1.payMethods.contains(PayMethods.CREDITCARD) } returns true
        every { rest1.payMethods.contains(PayMethods.DEBITCARD) } returns true
        every { rest1.payMethods.contains(PayMethods.MERCADOPAGO) } returns true
        every { rest1.payMethods.contains(PayMethods.PAYPAL) } returns true
        every { rest1.coordinates } returns coordsRest1
        every { rest1.code } returns 1
        every { rest1.name } returns "rest1"
        every { rest1.searchMenuByName("menu") } returns mutableListOf(menu1)
        every { rest1.menus } returns mutableListOf(menu1, menu2)
        every { rest1.products } returns mutableListOf()


        every { rest2.payMethods.contains(PayMethods.CASH) } returns true
        every { rest2.payMethods.contains(PayMethods.CREDITCARD) } returns false
        every { rest2.payMethods.contains(PayMethods.DEBITCARD) } returns false
        every { rest2.payMethods.contains(PayMethods.MERCADOPAGO) } returns false
        every { rest2.payMethods.contains(PayMethods.PAYPAL) } returns false
        every { rest2.coordinates} returns coordsRest2
        every { rest2.code } returns 2
        every { rest2.name } returns "rest2"
        every { rest2.searchMenuByName("menu") } returns mutableListOf(menu1, menu2)
        every { rest2.menus } returns mutableListOf(menu1, menu2, menu3)

        every { rest3.code } returns 3
        every { rest3.name } returns "bar"
        every { rest3.searchMenuByName("menu") } returns mutableListOf(menu1, menu2, menu3)

        app.addRestaurant(rest1)
        app.addRestaurant(rest2)
        app.addRestaurant(rest3)
    }

    @test fun searchRestaurantByCode() {
        assertEquals(rest1, app.searchRestaurantByCode(rest1.code))
    }


    @test fun searchOneRestaurantsByName() {
        assertEquals(1, app.searchRestaurantByName("rest1").size)
        assert(app.searchRestaurantByName("rest1").contains(rest1))
    }

    @test fun searchTwoRestaurantsByName() {
        assertEquals(2, app.searchRestaurantByName("rest").size)
    }

    @test fun searchRestaurantByCodeOrName() {
        val list = app.searchRestaurantByCodeOrName("bar", 2)

        assertEquals(2, list.size)
        assert(list.contains(rest2))
        assert(list.contains(rest3))
    }

    @test fun searchMenusByName() {
        val map = app.searchMenusByName("menu")

        assertEquals(map[rest1], mutableListOf(menu1))
        assertEquals(3, map.size)
        assertEquals(map[rest2], mutableListOf(menu1, menu2))

    }

    @test (expected = InvalidMenuCodeException::class) fun makeAnOrderExpectsInvalidMenuCodeException() {
        val menuList : MutableList<Menu> = mutableListOf(menu1, menu2, menu3)
        val payMethod = mockk<PayMethod>()

        every { payMethod.getPayMethod() } returns PayMethods.CREDITCARD

        app.makeAnOrder(user1, rest1, menuList, payMethod, destination)
    }

    @test (expected = MenuEmptyException::class) fun makeAnOrderExpectsMenuEmptyException() {
        val menu4 = mockk<Menu>()
        every { menu4.code } returns 77
        every { menu4.products } returns mutableListOf()
        every { rest2.menus } returns mutableListOf(menu1, menu2, menu3, menu4)
        val menuList : MutableList<Menu> = mutableListOf(menu1, menu2, menu3, menu4)
        val payMethod = mockk<PayMethod>()

        every { payMethod.getPayMethod() } returns PayMethods.CREDITCARD

        app.makeAnOrder(user1, rest2, menuList, payMethod, destination)
    }

    @test (expected = InvalidPayMethodException::class) fun makeAnOrderExpectsInvalidPayMethodException() {
        val menuList : MutableList<Menu> = mutableListOf(menu1, menu2, menu3)
        val payMethod = mockk<PayMethod>()

        every { payMethod.getPayMethod() } returns PayMethods.CREDITCARD

        app.makeAnOrder(user1, rest2, menuList, payMethod, destination)
    }
    @test (expected = RestaurantOutOfRangeException::class) fun makeAnOrderExpectsRestaurantOutOfRangeException() {
        val menuList : MutableList<Menu> = mutableListOf(menu1, menu2, menu3)
        val payMethod = mockk<PayMethod>()

        every { payMethod.getPayMethod() } returns PayMethods.CASH

        app.makeAnOrder(user1, rest2, menuList, payMethod, destination)
    }

    @test fun generateOrderCode(){
        assertEquals(0, app.generateOrderCode())
        assertEquals(1, app.generateOrderCode())
        assertEquals(2, app.generateOrderCode())
        assertEquals(3, app.generateOrderCode())
        assertEquals(4, app.generateOrderCode())
    }

    @test fun generateRestaurantCode(){
        assertEquals(0, app.generateRestaurantCode())
        assertEquals(1, app.generateRestaurantCode())
        assertEquals(2, app.generateRestaurantCode())
        assertEquals(3, app.generateRestaurantCode())
        assertEquals(4, app.generateRestaurantCode())
    }



}