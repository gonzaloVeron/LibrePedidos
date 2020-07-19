import io.javalin.Context
import java.time.LocalDate

class UserController() {

    fun verifyUser(ctx: Context) {
        try {
            val user = ctx.body<DataUser>()
            ctx.json(GeneralService.verifyUser(user))
        }
        catch (e: Exception) {
            GeneralService.handleException(ctx, e)
        }
    }

    fun addUser(ctx: Context) {
        try {
            val userFromCtx = ctx.body<NewUser>()
            val userToAdd = User(userFromCtx.username, userFromCtx.email, userFromCtx.password, LocalDate.now(), userFromCtx.address, userFromCtx.coord)
            ctx.json(GeneralService.addUser(userToAdd))
        }
        catch (e: Exception) {
            GeneralService.handleException(ctx, e)
        }
    }

    fun getUser(ctx: Context){
        try{
            val name = ctx.pathParam("id")
            val orders = ctx.queryParam("include")
            if(orders != null){
                if(orders == "orders"){
                    ctx.json(GeneralService.userWithoutPassword(GeneralService.findUser(name)))
                }
            }else{
                ctx.json(GeneralService.userWithoutOrdersAndPassword(GeneralService.findUser(name)))
            }
        }
        catch (e: Exception){
            GeneralService.handleException(ctx, e)
        }
    }

}