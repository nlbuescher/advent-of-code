package dev.buescher.adventofcode.mmxxv

import dev.buescher.adventofcode.core.*
import kotlin.math.*

@OptIn(ExperimentalUnsignedTypes::class)
object Day02 : Day("2025", "02") {
	private val pow10 = ulongArrayOf(
		1UL,
		10UL,
		100UL,
		1_000UL,
		10_000UL,
		100_000UL,
		1_000_000UL,
		10_000_000UL,
		100_000_000UL,
		1_000_000_000UL,
	)

	override fun solve(input: String): Solutions {
		val ids = processInput(input)

		val part1 = ids
			.filter { id ->
				val digitCount = floor(log10(id.toDouble())).toInt() + 1

				digitCount % 2 == 0 && (digitCount / 2).let { blockSize ->
					id / pow10[blockSize] == id % pow10[blockSize]
				}
			}
			.sum()

		val part2 = ids
			.filter { id ->
				val digitCount = floor(log10(id.toDouble())).toInt() + 1

				for (blockSize in 1..digitCount / 2) {
					val factor = pow10[digitCount - blockSize]

					val block = id / factor
					val result = (1..digitCount / blockSize).fold(0UL) { acc, _ ->
						acc * pow10[blockSize] + block
					}

					if (result == id) {
						return@filter true
					}
				}

				false
			}
			.sum()

		return Solutions(part1, part2)
	}

	private fun processInput(input: String): List<ULong> {
		return input.split(',').flatMap {
			it.split('-', limit = 2)
				.map(String::toULong)
				.let { (start, end) -> start..end }
		}
	}
}
