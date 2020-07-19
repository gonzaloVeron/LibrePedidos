import org.uqbar.commons.model.annotations.Observable

@Observable
data class Menu(val code : Int, var name : String = "", var description : String = "", val products : MutableList<Product> = mutableListOf(), var discountType: DiscountType? = null, var valDiscount : Int = 0, var state : Boolean = false) {

    fun getPrice(): Double {
        return calculateFinalPrice( products.sumBy { it.price }.toDouble())
    }

    fun calculateFinalPrice(finalPrice: Double): Double {
        return if(discountType == DiscountType.STATIC) finalPrice - valDiscount else finalPrice - ((finalPrice * valDiscount) / 100)
    }

    fun addProduct(product: Product) {
        this.products.add(product)
    }

}
