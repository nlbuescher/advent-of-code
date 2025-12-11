package dev.buescher.adventofcode

import dev.buescher.adventofcode.core.*
import dev.buescher.adventofcode.mmxxv.*
import kotlin.time.*

data class Run(val year: Int, val day: Int)

fun main(vararg args: String) {
	val runs = parseArguments(*args)

	var runtime = Duration.ZERO

	runs.forEach { (year, day) ->
		val solver = getSolver(year, day)
		val (solutions, elapsed) = measureTimedValue { solver.solve() }
		val (solution1, solution2) = solutions

		println("\n=== Day ${solver.day}")
		println("  • Part 1:  $solution1")
		println("  • Part 2:  $solution2")
		println("  • Elapsed: $elapsed")

		runtime += elapsed
	}

	println("\nTotal runtime: $runtime")
}

private fun getSolver(year: Int, day: Int): Day = when (year) {
	2025 -> getDay(day)
	else -> throw NotImplementedError("No solver for '$year:$day'.")
}

fun parseArguments(vararg args: String): List<Run> {
	if (args.isEmpty()) {
		error("No run argument provided. Expected '<year>:<day>' or '<year>:<startDay>-<endDay>'.")
	}

	return args.flatMap { arg ->
		val parts = Regex("""^(\d+):(\d+)(?:-(\d+))?$""")
			.matchEntire(arg)
			?.groupValues
			?.drop(1)
			?.map(String::toInt)

		when (parts?.size) {
			2 -> parts.let { (year, day) ->
				listOf(Run(year, day))
			}

			3 -> parts.let { (year, startDay, endDay) ->
				val range = if (startDay < endDay) startDay..endDay else endDay..startDay

				range.map { day -> Run(year, day) }
			}

			else -> {
				error("Invalid run argument '$arg'. Expected '<year>:<day>' or '<year>:<startDay>-<endDay>'.")
			}
		}
	}
}
