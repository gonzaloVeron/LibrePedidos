import com.fasterxml.jackson.databind.exc.MismatchedInputException
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import org.eclipse.jetty.http.HttpStatus.*

fun main() {

    val app = Javalin.create()
            .enableRouteOverview("/routes")
            .exception(MismatchedInputException::class.java) { e, ctx ->
                ctx.status(BAD_REQUEST_400)
                ctx.json(mapOf(
                        "status" to BAD_REQUEST_400,
                        "message" to e.message
                ))
            }
            .enableCorsForAllOrigins()
            .start(7000)

    app.get("/") { ctx ->
        ctx.json("It works!")
    }

    GeneralService.generateSources()

    app.routes {

        path("login") {
            post(GeneralService.userController::verifyUser)
        }

        path("register") {
            post(GeneralService.userController::addUser)
        }

        path("deliver") {
            post(GeneralService.deliverController::addDeliver)
            path(":id") {
                put(GeneralService.deliverController::voteOrder)
            }
        }

        path("users"){
            path(":id"){
                get(GeneralService.userController::getUser)
            }
        }

        path("restaurant"){
            path(":id"){
                get(GeneralService.restaurantController::getRestaurant)
            }
        }

        path("search"){
                get(GeneralService.searchController::getRestaurantAndMenu)
        }

    }
}


