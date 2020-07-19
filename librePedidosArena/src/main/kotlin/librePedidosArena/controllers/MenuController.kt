import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException


@Observable
class MenuController(var menu : Menu, var availableProducts : MutableList<Product>) {

    var availableProduct : Product? = null

    var selectedProduct : Product? = null

    var selected : MutableList<Product> = menu.products

    var discountSelected : DiscountType? = menu.discountType

    var price : Double = menu.getPrice()

    var code : Int = menu.code

    var name : String = menu.name

    var description : String = menu.description

    var availableDiscounts : MutableList<DiscountType> = mutableListOf(DiscountType.STATIC, DiscountType.PERCENTEGE)

    var valDiscount : Int = menu.valDiscount

    var state : Boolean = menu.state

    fun recalculatePrice(){
        this.price = this.priceOnWindow()
    }

    private fun obtainAvailableProduct() : Product {
        if(availableProduct != null)
            return this.availableProduct as Product
        else
            throw UserException("No hay un producto seleccionado")
    }

    private fun obtainSelectedProduct() : Product {
        if(selectedProduct != null)
            return this.selectedProduct as Product
        else
            throw UserException("No hay un producto seleccionado")
    }

    fun addAvailableProduct(){
        this.selected.add(this.obtainAvailableProduct())
        this.availableProduct = null
        this.recalculatePrice()
    }

    fun removeSelectedProduct(){
        this.selected.remove(this.obtainSelectedProduct())
        this.selectedProduct = null
        this.recalculatePrice()
    }

    fun buildMenu() : Menu {
        menu.discountType = this.discountSelected
        menu.name = this.name
        menu.valDiscount = this.valDiscount
        menu.description = this.description
        menu.state = this.state
        menu.getPrice()
        this.validateMenu()
        return menu
    }

    private fun priceOnWindow() : Double {
        return calculateFinalPrice(selected.map{ it.price }.sum().toDouble())
    }

    private fun calculateFinalPrice(finalPrice: Double): Double {
        return if(discountSelected == DiscountType.STATIC) Math.max(finalPrice - valDiscount, 0.0)
                else if(discountSelected == DiscountType.PERCENTEGE) Math.max(finalPrice - ((finalPrice * valDiscount) / 100), 0.0)
                        else finalPrice
    }

    private fun validateMenu(){
        if(this.name == "") throw NameFieldEmptyException()
            else if(this.price == 0.0) throw PriceFieldEmptyOrZeroException()
    }

    fun nameProducts() : String {
        var result = ""
        this.mapProducts().forEach { prod -> result += " - " + prod.value + " " + prod.key.name + " " + "$" + prod.key.price + "\n"}
        return result
    }

    private fun mapProducts() : MutableMap<Product, Int> {
        val map = mutableMapOf<Product, Int>()
        for(p in this.menu.products) {
            if(map.containsKey(p)) map.replace(p, map[p] as Int + 1) else map[p] = 1
        }
        return map
    }

}