package dev.buescher.adventofcode.mmxxv

import dev.buescher.adventofcode.core.*

object Day02 : Day("2025", "02") {
	override fun solve(input: String): Pair<Any?, Any?> {
		val ranges = processInput(input)

		val solution1 = ranges
			.flatMap { range ->
				range.filter { id ->
					val string = "$id"
					string.length % 2 == 0 &&
						(string.length / 2).let {
							string.take(it) == string.takeLast(it)
						}
				}
			}
			.sum()

		return solution1 to null
	}

	private fun processInput(input: String): List<ULongRange> {
		return input.split(',').map {
			val parts = it.split('-')
			val (start, end) = parts.map(String::toULong)
			start..end
		}
	}
}
