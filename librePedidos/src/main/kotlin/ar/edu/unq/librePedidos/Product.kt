import org.uqbar.commons.model.annotations.Observable

@Observable
data class Product(val code : Int, var name : String="", var description : String="", var price : Int=0, var category : Category?=null)