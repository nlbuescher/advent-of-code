import kotlin.math.pow
import kotlin.math.round

fun round(value: Float, places: Int): Float {
	val multiplier = 10f.pow(places)
	return round(value * multiplier) / multiplier
}

fun round(value: Double, places: Int): Double {
	val multiplier = 10.0.pow(places)
	return round(value * multiplier) / multiplier
}
