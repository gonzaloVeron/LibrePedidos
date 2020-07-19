abstract class DataPayMethod()

data class DataCreditCard(val name: String, val dni: String, val number: String, val securityCode: String, val type: String, val expiration: String): DataPayMethod()

data class DataDebitCard(val name: String, val dni: String, val number: String, val securityCode: String, val type: String, val expiration: String): DataPayMethod()

class DataCash: DataPayMethod()
