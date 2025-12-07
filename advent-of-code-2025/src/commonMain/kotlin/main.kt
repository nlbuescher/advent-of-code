import kotlin.time.*

@OptIn(ExperimentalTime::class)
fun main(vararg args: String) {
	if (args.isEmpty()) {
		error("Please provide at least one day to run.")
	}

	val days = args.map { arg ->
		arg.toIntOrNull()
			?.let { day ->
				if (day !in 1..25) {
					error("Day '$day' is not between 1 and 25.")
				}

				getDay(day)
			}
			?: error("Day '$arg' is not an integer.")
	}

	var runtime = Duration.ZERO

	days.forEach { day ->
		val (solutions, elapsed) = measureTimedValue { day.solve() }

		println("\n=== Day $day")
		println("  • Part 1: ${solutions.first}")
		println("  • Part 2: ${solutions.second}")
		println("  • Elapsed: $elapsed")

		runtime += elapsed
	}

	println("\nTotal runtime: $runtime")
}

fun getDay(day: Int): Day = when (day) {
	1 -> Day01
	2 -> Day02
	3 -> Day03
	4 -> Day04
	5 -> Day05
	6 -> Day06
	7 -> Day07
	8 -> Day08
	9 -> Day09
	10 -> Day10
	11 -> Day11
	12 -> Day12
	13 -> Day13
	14 -> Day14
	15 -> Day15
	16 -> Day16
	17 -> Day17
	18 -> Day18
	19 -> Day19
	20 -> Day20
	21 -> Day21
	22 -> Day22
	23 -> Day23
	24 -> Day24
	25 -> Day25
	else -> throw NotImplementedError()
}
