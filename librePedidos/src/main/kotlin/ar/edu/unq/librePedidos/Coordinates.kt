import org.uqbar.commons.model.annotations.Observable

@Observable
data class Coordinates(val latitude: Double, val longitude: Double, val name: String = "")

