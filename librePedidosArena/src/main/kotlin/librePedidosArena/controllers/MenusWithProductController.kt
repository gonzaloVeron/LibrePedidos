import org.uqbar.commons.model.annotations.Observable

@Observable
class MenusWithProductController(val product: Product, var menus : MutableList<Menu>) {

    val productName = product.name

    fun menusWithProduct() : String {
        var result = ""
        this.menus.forEach{ menu -> if(menu.products.contains(product)) result += "Menu: " + menu.name + "\n" }
        return result
    }

}