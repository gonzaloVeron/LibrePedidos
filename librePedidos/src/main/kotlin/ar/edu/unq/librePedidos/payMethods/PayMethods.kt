import org.uqbar.commons.model.annotations.Observable

@Observable
enum class PayMethods {
    CASH, CREDITCARD, DEBITCARD, PAYPAL, MERCADOPAGO
}

abstract class PayMethod {
    abstract fun getPayMethod(): PayMethods
}

data class CreditCard(val name: String, val dni: String, val number: String, val securityCode: String, val type: String, val expiration: String): PayMethod() {
    override fun getPayMethod(): PayMethods = PayMethods.CREDITCARD
}

data class DebitCard(val name: String, val dni: String, val number: String, val securityCode: String, val type: String, val expiration: String): PayMethod() {
    override fun getPayMethod(): PayMethods = PayMethods.DEBITCARD
}

class Cash: PayMethod() {
    override fun getPayMethod(): PayMethods = PayMethods.CASH
}