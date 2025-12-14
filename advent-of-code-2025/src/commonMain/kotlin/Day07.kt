package dev.buescher.adventofcode.mmxxv

import dev.buescher.adventofcode.core.*

object Day07 : Day("2025", "07") {
	override fun solve(input: String): Solutions {
		val part1 = Grid(input.lines())
			.rows()
			.map { it.toList() }
			.windowed(2)
			.fold(0) { totalSplitCount, (previousRow, row) ->
				totalSplitCount + row.indices.fold(0) { rowSplitCount, i ->
					when (previousRow[i].value) {
						'S', '|' -> when (row[i].value) {
							'^' -> {
								row[i - 1].value = '|'
								row[i + 1].value = '|'
								return@fold rowSplitCount + 1
							}

							else -> {
								row[i].value = '|'
							}
						}
					}
					rowSplitCount
				}
			}

		val part2 = null

		return Solutions(part1, part2)
	}
}
