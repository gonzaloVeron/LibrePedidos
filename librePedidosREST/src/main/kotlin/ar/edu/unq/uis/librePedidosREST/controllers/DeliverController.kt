import io.javalin.Context

class DeliverController {

    fun addDeliver(ctx: Context) {

        try {
            val dataOrder = ctx.body<DataOrder>()
            val user = GeneralService.findUser(dataOrder.user)
            val restaurant = GeneralService.getRestaurant(dataOrder.restaurantId)
            val menus = getMenus(dataOrder.menus, restaurant)
            val payMethod = dataOrder.payMethod.getPayMethod()
            val destination = dataOrder.destination

            ctx.json(GeneralService.makeAnOrder(user, restaurant, menus, payMethod, destination))
        }
        catch (e: Exception) {
            GeneralService.handleException(ctx, e)
        }

    }

    fun voteOrder(ctx: Context) {

        try {
            val dataVoteOrder = ctx.body<DataVoteOrder>()
            val user = GeneralService.findUser(dataVoteOrder.user)
            ctx.json(user.voteOrder(ctx.pathParam("id").toInt(), dataVoteOrder.rating))
        }
        catch (e: Exception) {
            GeneralService.handleException(ctx, e)
        }

    }

    private fun getMenus(menus: List<DataOrderMenu>, restaurant: Restaurant): MutableList<Menu> {
        val menusToReturn = mutableListOf<Menu>()

        for(menu in menus) {
            (1..menu.amount).forEach {menusToReturn.add(restaurant.searchMenuByCode(menu.menuId))}
        }
        return menusToReturn
    }

}