import java.rmi.UnexpectedException

class SomePayMethod(val creditCard: DataCreditCard?, val debitCard: DataDebitCard?, val dataCash: DataCash?) {

    fun getPayMethod(): PayMethod {
        val data = this.payMethodSelected()

        return when(data) {
            is DataCreditCard -> CreditCard(data.name, data.dni, data.number, data.securityCode, data.type, data.expiration)
            is DataDebitCard -> DebitCard(data.name, data.dni, data.number, data.securityCode, data.type, data.expiration)
            is DataCash -> Cash()
            else -> throw UnexpectedException("La orden no tiene un metodo de pago valido")
        }
    }

    private fun payMethodSelected(): DataPayMethod? {
        return creditCard ?: dataCash ?: debitCard
    }

}