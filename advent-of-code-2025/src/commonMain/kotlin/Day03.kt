package dev.buescher.adventofcode.mmxxv

import dev.buescher.adventofcode.core.*

object Day03 : Day("2025", "03") {
	override fun solve(input: String): Solutions {
		val banks = input.lines()

		fun findJoltage(batteryCount: Int) = banks.sumOf { bank ->
			buildString {
				val (leftIndex, leftBattery) = bank
					.dropLast(batteryCount - 1)
					.foldIndexed(-1 to '0') { index, (foundIndex, foundBattery), battery ->
						if (battery > foundBattery) index to battery else foundIndex to foundBattery
					}

				append(leftBattery)

				(batteryCount - 2 downTo 0)
					.toList()
					.fold(leftIndex + 1) { searchEndIndex, searchPadding ->
						val (rightIndex, rightBattery) = bank
							.drop(searchEndIndex)
							.dropLast(searchPadding)
							.foldRightIndexed(-1 to '0') { index, battery, (foundIndex, foundBattery) ->
								if (battery >= foundBattery) index to battery else foundIndex to foundBattery
							}

						append(rightBattery)

						searchEndIndex + rightIndex + 1
					}
			}.toULong()
		}

		val part1 = findJoltage(2)
		val part2 = findJoltage(12)

		return Solutions(part1, part2)
	}
}
