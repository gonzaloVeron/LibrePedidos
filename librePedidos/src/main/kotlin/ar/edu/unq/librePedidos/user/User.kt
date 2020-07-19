
import java.time.LocalDate

open class User(val nameUser: String, val email: String, val password: String, val registrationDate : LocalDate, var address: String, var coordinates: Coordinates, var ordersPlaced: MutableList<Order> = mutableListOf()){

    fun makeAnOrder(order : Order){
        this.ordersPlaced.add(order)
    }

    fun searchOrderByCode(code: Int): Order {
        return ordersPlaced.find { it.code == code } ?: throw OrderNotFoundException()
    }

    fun voteOrder(orderId: Int, vote: Int): Order {
        val order = this.searchOrderByCode(orderId)
        order.setRating(vote)
        return order
    }

}