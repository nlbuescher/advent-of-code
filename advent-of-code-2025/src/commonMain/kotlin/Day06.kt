package dev.buescher.adventofcode.mmxxv

import dev.buescher.adventofcode.core.*

object Day06 : Day("2025", "06") {
	override fun solve(input: String): Solutions {
		val operations = processInput(input)

		val part1 = operations.sumOf { (operator, operands) ->
			when (operator) {
				Computation.Operator.Plus -> operands.reduce(ULong::plus)
				Computation.Operator.Minus -> operands.reduce(ULong::minus)
				Computation.Operator.Times -> operands.reduce(ULong::times)
				Computation.Operator.Divide -> operands.reduce(ULong::div)
			}
		}

		val part2 = null

		return Solutions(part1, part2)
	}

	data class Computation(val operator: Operator, val operands: List<ULong>) {
		enum class Operator { Plus, Minus, Times, Divide }
	}

	fun processInput(input: String): List<Computation> =
		input.lines()
			.map { it.split(" ").filter(String::isNotEmpty) }
			.let { table ->
				if (table.isEmpty()) return@let emptyList()

				(0 until table.first().size).map { column ->
					(0 until table.size).map { row ->
						table[row][column]
					}
				}
			}
			.map {
				val operator = when (it.last()) {
					"+" -> Computation.Operator.Plus
					"-" -> Computation.Operator.Minus
					"*" -> Computation.Operator.Times
					"/" -> Computation.Operator.Divide
					else -> error("unknown operator")
				}

				Computation(operator, it.dropLast(1).map(String::toULong))
			}
}
