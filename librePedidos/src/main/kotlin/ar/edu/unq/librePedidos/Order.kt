import org.uqbar.commons.model.annotations.Observable
import java.time.LocalDate

@Observable
class Order(val code: Int, val date: LocalDate, val restaurant: Restaurant, val menus: MutableList<Menu>, val state: OrderState, var payMethod: PayMethod, val destination: Coordinates, var rating: Int? = null, var rated: Boolean = false) {
    fun setRating(rating: Int) {
        if(!this.rated) {
            this.rating = rating
            this.rated = true
        }
        else
            throw OrderAlreadyVotedException()
    }
}