import io.javalin.Context
import org.eclipse.jetty.http.HttpStatus

class RestaurantController {

    fun getRestaurant(ctx: Context){
        try{
            val id = ctx.pathParam("id").toInt()
            val restaurant = GeneralService.getRestaurant(id)
            ctx.json(restaurant)
        }
        catch (e: Exception){
            GeneralService.handleException(ctx, e)
        }
    }

}