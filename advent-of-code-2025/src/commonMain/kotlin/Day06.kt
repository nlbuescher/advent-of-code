package dev.buescher.adventofcode.mmxxv

import dev.buescher.adventofcode.core.*
import dev.buescher.adventofcode.core.Grid.Row.Order.*

object Day06 : Day("2025", "06") {
	override fun solve(input: String): Solutions {
		val part1 = input.lines()
			.map { it.split(" ").filter(String::isNotEmpty) }
			.let { table ->
				if (table.isEmpty()) return@let emptyList()

				(0 until table.first().size).map { column ->
					(0 until table.size).map { row ->
						table[row][column]
					}
				}
			}
			.fold(mutableListOf<ULong>()) { results, it ->
				results.apply {
					val operation: (ULong, ULong) -> ULong = when (it.last()) {
						"+" -> ULong::plus
						"-" -> ULong::minus
						"*" -> ULong::times
						"/" -> ULong::div
						else -> error("unknown operator")
					}

					add(it.dropLast(1).map(String::toULong).reduce(operation))
				}
			}
			.sum()

		val part2 = Grid(input.lines()).columns(rowOrder = RightToLeft).toList().let { columns ->
			val results = mutableListOf<ULong>()
			val operands = mutableListOf<ULong>()
			val operand = StringBuilder()
			for (column in columns) {
				for (cell in column) {
					if (!operand.isEmpty() && !cell.value.isDigit()) {
						operands.add(operand.toString().toULong())
						operand.clear()
					}

					if (cell.value in "+-*/") {
						val operation: (ULong, ULong) -> ULong = when (cell.value) {
							'+' -> ULong::plus
							'-' -> ULong::minus
							'*' -> ULong::times
							'/' -> ULong::div
							else -> error("unreachable")
						}
						results.add(operands.reduce(operation))
						operands.clear()
					}

					if (cell.value.isDigit()) {
						operand.append(cell.value)
					}
				}
			}

			results.sum()
		}

		return Solutions(part1, part2)
	}
}
