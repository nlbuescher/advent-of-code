package dev.buescher.adventofcode.mmxxv

import dev.buescher.adventofcode.core.*

fun getDay(day: Int): Day = when (day) {
	1 -> Day01
	2 -> Day02
	3 -> Day03
	4 -> Day04
	5 -> Day05
	6 -> Day06
	7 -> Day07
	8 -> Day08
	9 -> Day09
	10 -> Day10
	11 -> Day11
	12 -> Day12
	else -> throw NotImplementedError()
}
