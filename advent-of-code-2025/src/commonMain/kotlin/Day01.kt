package dev.buescher.adventofcode.mmxxv

import dev.buescher.adventofcode.core.*

object Day01 : Day("2025", "01") {
	private const val DIAL_START = 50
	private const val DIAL_SIZE = 100

	override fun solve(input: String): Pair<Any?, Any?> {
		var solution1 = 0
		var solution2 = 0

		processInput(input).fold(DIAL_START) { lastPosition, rotation ->
			val nextPosition = lastPosition + rotation

			if (nextPosition.mod(DIAL_SIZE) == 0) {
				solution1 += 1
			}

			if (rotation > 0) {
				solution2 += nextPosition / DIAL_SIZE
			}
			else {
				if (lastPosition == 0) {
					solution2 -= 1
				}

				solution2 += (DIAL_SIZE - nextPosition) / DIAL_SIZE
			}

			nextPosition.mod(100)
		}

		return solution1 to solution2
	}

	private fun processInput(input: String): List<Int> {
		return input
			.lines()
			.map {
				if (it.startsWith('L')) {
					it.substringAfter("L").toInt().unaryMinus()
				}
				else if (it.startsWith('R')) {
					it.substringAfter("R").toInt()
				}
				else error("Invalid input: '$it'. Expected 'L' or 'R' prefix.")
			}
	}
}
