package dev.buescher.adventofcode.mmxxv

import dev.buescher.adventofcode.core.*

object Day04 : Day("2025", "04") {
	override fun solve(input: String): Solutions {
		val grid = Grid(input.lines())

		val part1 = grid.rows().flatten()
			.filter { it.value != '.' }
			.count { cell ->
				val neighborCount = cell.neighbors()
					.count { neighbor -> cell.value == neighbor.value }

				neighborCount < 4
			}

		var part2 = 0

		var removed: Int
		do {
			removed = grid.rows().flatten()
				.filter { it.value != '.' }
				.count { cell ->
					val neighborCount = cell.neighbors()
						.count { neighbor -> cell.value == neighbor.value }

					if (neighborCount < 4) {
						cell.value = 'x'
						return@count true
					}
					else return@count false
				}

			part2 += removed

			grid.rows().flatten()
				.filter { it.value == 'x' }
				.forEach { it.value = '.' }
		}
		while (removed != 0)

		return Solutions(part1, part2)
	}
}
