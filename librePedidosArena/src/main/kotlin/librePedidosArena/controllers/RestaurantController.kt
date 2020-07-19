import org.uqbar.commons.model.exceptions.UserException
import org.uqbar.commons.model.annotations.Observable

@Observable
class RestaurantController(var restaurant: Restaurant) {
    val name = restaurant.name

    var products = restaurant.products

    var menus : MutableList<Menu> = restaurant.menus

    var selectedProduct : Product? = null

    var selectedMenu : Menu? = null

    var productToFind : String = ""

    var menuToFind : String = ""

    fun searchProductsByName(){
        this.products = restaurant.searchProductsByName(productToFind)
    }

    fun searchMenuByName(){
        this.menus = restaurant.searchMenuByName(menuToFind)
    }

    fun generateMenuCode(): Int {
        return restaurant.generateMenuCode()
    }

    fun newMenu(): Menu {
        return Menu(restaurant.generateMenuCode(), "", "", mutableListOf(), DiscountType.STATIC, 0, false)
    }

    fun addMenu(menu: Menu) {
        this.restaurant.addMenu(menu)
    }

    fun newProduct(): Product {
        return Product(restaurant.generateProductCode())
    }

    fun addProduct(product: Product){
        this.restaurant.addProduct(product)
    }

    fun updateMenus() {
        this.menus = mutableListOf()
        this.menus = this.restaurant.menus
    }

    fun updateProducts() {
        this.products = mutableListOf()
        this.products = this.restaurant.products
    }

    fun obtainSelectedMenu(): Menu {
        if(selectedMenu != null)
            return this.selectedMenu as Menu
        else
            throw MenuNotSelectedException()
    }

    fun obtainSelectedProduct(): Product {
        if(selectedProduct != null)
            return this.selectedProduct as Product
        else
            throw ProductNotSelectedException()
    }

    fun removeMenu(menu: Menu?) {
        this.restaurant.menus.remove(menu)
    }

    fun removeProduct(product: Product?) {
        if(this.productIsOnAnyMenu(product as Product))
            throw ProductInAnyMenuException()
        else
            this.restaurant.products.remove(product)
    }

    fun productIsOnAnyMenu(product: Product): Boolean {
        return this.restaurant.menus.any{ p -> p.products.contains(product) }
    }

}