import org.uqbar.commons.model.annotations.Observable
import java.lang.Math.*

@Observable
object GeographicalCoordsCalculator {
    private const val EARTH_RADIUS: Double = 6371.0

    fun distance(geo1: Coordinates, geo2: Coordinates): Double {
        val dLat = deg2rad(geo2.latitude - geo1.latitude)
        val dLon = deg2rad(geo2.longitude - geo1.longitude)
        val temp1: Double = pow(sin(dLat/2), 2.0)
        val temp2: Double = cos(deg2rad(geo1.latitude)) * cos(deg2rad(geo2.latitude)) * pow(sin(dLon/2), 2.0)
        val a = temp1 + temp2
        val aTan: Double = 2 * atan2(sqrt(a), sqrt(1 - a))
        val res = aTan * EARTH_RADIUS
        return floor(res * 100) / 100
    }

    private fun deg2rad(dec: Double): Double = dec * (PI / 180)
}