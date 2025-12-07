import kotlin.time.*

@OptIn(ExperimentalTime::class)
fun main(vararg args: String) {
	if (args.isEmpty()) {
		error("Please provide at least one day to run.")
	}

	val days = buildList {
		args.forEach { arg ->
			if (arg.matches(Regex("""^\d+-\d+$"""))) {
				val (start, end) = arg.split('-').map(String::toInt)

				if (start !in 1..25 || end !in 1..25 || start > end) {
					error("Day range '$arg' is not between 1 and 25 or start is greater than end.")
				}

				addAll((start..end).map(::getDay))
			}
			else if (arg.matches(Regex("""^\d+$"""))) {
				val day = arg.toInt()

				if (day !in 1..25) {
					error("Day '$day' is not between 1 and 25.")
				}

				add(getDay(day))
			}
			else {
				error("Invalid day argument '$arg'. Expected a day number or a range of days.")
			}
		}
	}

	var runtime = Duration.ZERO

	days.forEach { day ->
		val (solutions, elapsed) = measureTimedValue { day.solve() }

		println("\n=== $day")
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
