import org.uqbar.commons.model.annotations.Observable

@Observable
class Restaurant (val code : Int, var name : String, var description : String = "", var dir : String, var coordinates: Coordinates, var menus : MutableList<Menu> = mutableListOf(), var products : MutableList<Product> = mutableListOf(), var supervisor : UserAdmin? = null) {

    var nextMenuCode = 0
    var nextProductCode = 0

    var payMethods : MutableList<PayMethods> = mutableListOf()

    fun addPayMethod(method : PayMethods){
        payMethods.add(method)
    }

    fun removePayMethod(method : PayMethods) {
        payMethods.remove(method)
    }

    fun searchMenuByName(name : String) : MutableList<Menu> {
        return menus.filter { m -> m.name.contains(name) }.toMutableList()
    }

    fun searchMenuByCode(code: Int): Menu {
        return menus.find { m -> m.code == code } ?: throw MenuNotFoundException()
    }

    fun searchProductsByName(name : String) : MutableList<Product> {
        return products.filter { p -> p.name.contains(name) }.toMutableList()
    }

    fun generateMenuCode() : Int {
        val code: Int = this.nextMenuCode
        this.nextMenuCode++
        return code
    }

    fun generateProductCode() : Int {
        val code: Int = this.nextProductCode
        this.nextProductCode++
        return code
    }

    fun addMenu(menu: Menu) {
        this.menus.add(menu)
    }

    fun removeMenu(menu: Menu) {
        this.menus.remove(menu)
    }

    fun addProduct(product: Product) {
        this.products.add(product)
    }

    fun removeProduct(product: Product) {
        this.products.remove(product)
    }




}