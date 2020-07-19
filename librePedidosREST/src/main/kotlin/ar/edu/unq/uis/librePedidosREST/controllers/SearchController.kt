import io.javalin.Context

class SearchController {

    fun getRestaurantAndMenu(ctx: Context){
        try{
            val q = ctx.queryParam("q") ?: throw IncorrectParametersForQueryRequestException()
            val lat = ctx.queryParam("lat")
            val long = ctx.queryParam("long")
            if(lat == null || long == null){
                ctx.json(DataRestaurantAndMenu(GeneralService.searchRestaurantAndMenu(q), GeneralService.searchMenu(q)))

            }else{
                ctx.json(DataRestaurantAndMenu(GeneralService.searchRestaurantAndMenu(q, lat.toDouble(), long.toDouble()), GeneralService.searchMenu(q)))
            }

        }

        catch (e: Exception){
            GeneralService.handleException(ctx, e)
        }
    }



    fun getMenus(ctx: Context){
        try{
            val id = ctx.pathParam("id").toInt()
            val menu = GeneralService.getRestaurant(id)
            ctx.json(menu)
        }
        catch (e: Exception){
            GeneralService.handleException(ctx, e)
        }
    }

}