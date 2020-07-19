import org.uqbar.commons.model.annotations.Observable

@Observable
class ProductController( var product: Product) {

    var code : Int = product.code
    var name : String = product.name
    var description : String = product.description
    var price : Int = product.price
    var category : Category? = product.category
    var categories : MutableList<Category> = mutableListOf(Category.DESSERT, Category.ENTRY, Category.MAIN_DISH, Category.SOFT_DRINK)

    fun buildProduct() : Product{
        product.name = this.name
        product.description = this.description
        product.price = this.price
        product.category = this.category
        this.validateProduct()
        return product
    }

    fun validateProduct(){
        if(this.name == "") throw NameFieldEmptyException()
            else if(this.category == null) throw CategoryFieldEmptyException()
                    else if(this.price == 0) throw PriceFieldEmptyOrZeroException()

    }

}