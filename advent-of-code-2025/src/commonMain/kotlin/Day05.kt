package dev.buescher.adventofcode.mmxxv

import dev.buescher.adventofcode.core.*
import kotlin.math.*

object Day05 : Day("2025", "05") {
	override fun solve(input: String): Solutions {
		val (freshIdRanges, availableIds) = processInput(input)

		val part1 = availableIds.count { availableId ->
			freshIdRanges.any { it.contains(availableId) }
		}

		val part2 = mutableSetOf<ULongRange>()
			.also { results ->
				freshIdRanges.forEach { range ->
					val existingRanges = results.filter {
						range.first in it || range.last in it
							|| it.first in range || it.last in range
					}

					if (existingRanges.isEmpty()) {
						results.add(range)
					}
					else {
						existingRanges.forEach {
							results.remove(it)
						}

						val newStart = existingRanges.fold(range.first) { start, existingRange ->
							min(start, existingRange.first)
						}

						val newEnd = existingRanges.fold(range.last) { end, existingRange ->
							max(end, existingRange.last)
						}

						results.add(newStart..newEnd)
					}
				}
			}
			.sumOf { it.last - it.first + 1UL }

		return Solutions(part1, part2)
	}

	private fun processInput(input: String): Pair<List<ULongRange>, List<ULong>> =
		input.split("\n\n", limit = 2)
			.map(String::lines)
			.let { (freshRangeStrings, availableIds) ->
				val freshIds = freshRangeStrings
					.map { range ->
						range.split('-', limit = 2)
							.map(String::toULong)
							.let { (start, end) -> start..end }
					}

				freshIds to availableIds.map(String::toULong)
			}
}
