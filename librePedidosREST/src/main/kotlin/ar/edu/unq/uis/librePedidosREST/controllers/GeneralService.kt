import Exceptions.*
import io.javalin.Context
import org.eclipse.jetty.http.HttpStatus
import java.time.LocalDate

object GeneralService {

    // Model
    val app = App()
    val userManager = UserManager()

    // Controllers
    val userController = UserController()
    val deliverController = DeliverController()
    val restaurantController = RestaurantController()
    val searchController = SearchController()

    // Datos hardcodeados de prueba:
    val restaurant1 = Restaurant(1, "rest1", "bar", "avellaneda", Coordinates(123.0, -123.0))
    val menu1 = Menu(restaurant1.generateMenuCode(), "Papitas fritas")
    val menu2 = Menu(restaurant1.generateMenuCode(), "Quesadilla")
    val menu3 = Menu(restaurant1.generateMenuCode(), "Pollo al limon")
    val producto1 = Product(restaurant1.generateProductCode(), "producto")
    val order1 = Order(1, LocalDate.now(), restaurant1, mutableListOf(menu1, menu2), OrderState.PENDING, Cash(), Coordinates(123.0, -123.0))
    val order2 = Order(2, LocalDate.now(), restaurant1, mutableListOf(menu1), OrderState.DELIVERED, Cash(), Coordinates(123.0, -123.0))
    val order3 = Order(3, LocalDate.now(), restaurant1, mutableListOf(menu1, menu2, menu3), OrderState.PENDING, Cash(), Coordinates(123.0, -123.0))
    val order4 = Order(4, LocalDate.now(), restaurant1, mutableListOf(menu1), OrderState.DELIVERED, Cash(), Coordinates(123.0, -123.0))
    val order5 = Order(5, LocalDate.now(), restaurant1, mutableListOf(menu1), OrderState.PENDING, Cash(), Coordinates(123.0, -123.0))
    val usuario1 = User("pepe", "pepe@pepe.com", "123", LocalDate.now(), "Calle falsa 123", Coordinates(123.0, -123.0), mutableListOf(order1, order2, order3, order4, order5))

    fun generateSources() {
        menu1.addProduct(producto1)
        restaurant1.addMenu(menu1)
        restaurant1.addMenu(menu2)
        restaurant1.addMenu(menu3)
        restaurant1.addPayMethod(PayMethods.CREDITCARD)
        restaurant1.addPayMethod(PayMethods.DEBITCARD)
        restaurant1.addPayMethod(PayMethods.CASH)
        app.addRestaurant(restaurant1)
        userManager.addUser(usuario1)
    }

    fun searchRestaurantAndMenu(q: String, lat: Double, long: Double): List<DataRestaurant>{
        val coord = Coordinates(lat, long)
        return app.searchRestaurantByName(q).filter{app.verifyThatItIsInARangeOf20Kilometers(coord, it)}.map{ DataRestaurant(it.code, it.name, it.dir, it.coordinates, it.description, it.menus) }
    }

    fun searchRestaurantAndMenu(q: String): List<DataRestaurant>{
        return app.searchRestaurantByName(q).map{ DataRestaurant(it.code, it.name, it.dir, it.coordinates, it.description, it.menus) }
    }

    fun searchMenu(q: String): MutableList<DataMenu>{
        val map = app.searchMenusByName(q)
        val dataMenuList = mutableListOf<DataMenu>()
        map.forEach{ dataMenuList.addAll( toDataMenuList(it.value, it.key) ) }
        return dataMenuList
    }

    fun toDataMenuList(menus : MutableList<Menu>, restaurant: Restaurant): MutableList<DataMenu>{
        return menus.map {DataMenu(it.code, it.name, it.getPrice().toInt(), it.products, restaurant.code)}.toMutableList()
    }

    fun handleException(ctx: Context, exception: Exception) {

        val status = when (exception) {
            is NotFoundException,
            is InvalidMenuCodeException -> HttpStatus.NOT_FOUND_404

            is RestaurantOutOfRangeException,
            is UserOrPasswordIncorrectException,
            is OrderAlreadyVotedException,
            is MenuEmptyException,
            is IncorrectParametersForQueryRequestException -> HttpStatus.BAD_REQUEST_400

            is InvalidPayMethodException -> HttpStatus.PAYMENT_REQUIRED_402

            else -> HttpStatus.BAD_REQUEST_400
        }

        ctx.status(status)

        ctx.json(
                mapOf(
                        "status " to status,
                        "Error: " to exception.message
                )
        )
    }

    fun getRestaurant(id: Int): Restaurant {
        return app.searchRestaurantByCode(id)
    }

    fun verifyUser(dataUser: DataUser): User {
        return userManager.verifyUser(dataUser.username, dataUser.password)
    }

    fun makeAnOrder(user: User, restaurant: Restaurant, menus: MutableList<Menu>, payMethod: PayMethod, destination: Coordinates): Order {
        return app.makeAnOrder(user, restaurant, menus, payMethod, destination)
    }

    fun addUser(userToAdd: User): User{
        return userManager.addUser(userToAdd)
    }

    fun findUser(name: String): User{
        return userManager.findUserByName(name)
    }

    fun userWithoutPassword(user: User): UserWithoutPassword {
        return UserWithoutPassword(user.nameUser, user.email, user.address, user.coordinates, user.ordersPlaced.filter{it.state == OrderState.DELIVERED}, user.ordersPlaced.filter{it.state == OrderState.PENDING})
    }

    fun userWithoutOrdersAndPassword(user: User): UserWithoutOrdersAndPassword {
        return UserWithoutOrdersAndPassword(user.nameUser, user.email, user.address, user.coordinates)
    }

}