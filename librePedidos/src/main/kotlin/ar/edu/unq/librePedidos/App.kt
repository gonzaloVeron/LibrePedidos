import Exceptions.*
import org.uqbar.commons.model.annotations.Observable
import java.time.LocalDate

@Observable
class App (val userManager: UserManager = UserManager(), var restaurants : MutableList<Restaurant> = mutableListOf(), var payMethods: MutableList<PayMethods> = mutableListOf()) {

    var nextOrderCode : Int = 0
    var nextRestaurantCode : Int = 0

    fun addRestaurant(restaurant: Restaurant) {
        restaurants.add(restaurant)
    }

    fun removeRestaurant(restaurant: Restaurant) {
        restaurants.remove(restaurant)
    }

    fun searchRestaurantByCode(code : Int) : Restaurant {
        try {
            return restaurants.find { r -> r.code == code } as Restaurant
        }
        catch (e: Exception) {
            throw RestaurantNotFoundException()
        }
    }

    fun searchRestaurantByName(name : String) : List<Restaurant> {
        return restaurants.filter{ r -> r.name.contains(name) }
    }

    fun searchRestaurantByCodeOrName(name : String, code : Int) : List<Restaurant> {
        return restaurants.filter{ r -> r.name.contains(name) || r.code == code}
    }

    fun searchMenusByName(name : String) : MutableMap<Restaurant, MutableList<Menu>> {
        val result : MutableMap<Restaurant, MutableList<Menu>> = mutableMapOf()
        restaurants.forEach{ r -> result.put(r,r.searchMenuByName(name))}

        return result.filter { r -> r.value.isNotEmpty()}.toMutableMap()
    }

    fun makeAnOrder(user : User, restaurant : Restaurant, menus : MutableList<Menu>, payMethod: PayMethod, destination: Coordinates): Order {
        this.verifyMenuWithRestaurant(menus, restaurant)
        this.verifyAllMenusArentEmpty(menus)
        this.verifyPayMethods(payMethod, restaurant)
        this.verifyThatItIsInARangeOf20Kilometers(user, restaurant)

        val order = Order(generateOrderCode(), LocalDate.now(), restaurant, menus, OrderState.PENDING, payMethod, destination)
        user.makeAnOrder(order)

        return order
    }

    private fun verifyCodeMenuAndRestaurant(codeMenu : Int, restaurant : Restaurant ){
        if(!restaurant.menus.any { it.code == codeMenu }){
            throw InvalidMenuCodeException()
        }
    }

    fun verifyMenuWithRestaurant(menus:List<Menu>, restaurant: Restaurant){
        menus.forEach{this.verifyCodeMenuAndRestaurant(it.code, restaurant)}
    }

    fun verifyAllMenusArentEmpty(menus: List<Menu>) {
        menus.forEach { m -> if(m.products.isEmpty()) throw MenuEmptyException() }
    }

    fun verifyPayMethods(payMethod: PayMethod, restaurant: Restaurant){
        if(!restaurant.payMethods.contains(payMethod.getPayMethod())){
            throw InvalidPayMethodException()
        }
    }

    fun verifyThatItIsInARangeOf20Kilometers (user : User, restaurant : Restaurant) : Unit {
        if(GeographicalCoordsCalculator.distance(user.coordinates, restaurant.coordinates) > 20){
            throw RestaurantOutOfRangeException()
        }
    }

    fun verifyThatItIsInARangeOf20Kilometers (coordinates: Coordinates, restaurant : Restaurant) : Boolean {
        return GeographicalCoordsCalculator.distance(coordinates, restaurant.coordinates) <= 20
    }

    fun generateOrderCode() : Int {
        val code: Int = this.nextOrderCode
        this.nextOrderCode++
        return code
    }

    fun generateRestaurantCode() : Int {
        val code: Int = this.nextRestaurantCode
        this.nextRestaurantCode++
        return code
    }

}