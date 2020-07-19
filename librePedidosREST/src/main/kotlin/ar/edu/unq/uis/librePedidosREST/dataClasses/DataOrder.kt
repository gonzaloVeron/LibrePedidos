data class DataOrder(val user: String, val restaurantId: Int, val menus: MutableList<DataOrderMenu>, val payMethod: SomePayMethod, val destination: Coordinates)
