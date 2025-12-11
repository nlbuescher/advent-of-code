package dev.buescher.adventofcode.mmxxv

import dev.buescher.adventofcode.core.*

object Day04 : Day("2025", "04") {
	override fun solve(input: String): Solutions {
		val grid = processInput(input)

		val part1 = grid
			.filter { it.value == '@' }
			.count { cell ->
				cell.neighbors()
					.count { neighbor -> cell.value == neighbor.value }
					.let { neighborCount -> neighborCount < 4 }
			}

		val part2 = null

		return Solutions(part1, part2)
	}

	fun processInput(input: String): Grid = Grid(input.trim().lines())
}
