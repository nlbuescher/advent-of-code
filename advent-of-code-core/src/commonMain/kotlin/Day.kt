package dev.buescher.adventofcode.core

import kotlinx.io.*
import kotlinx.io.files.*

interface Day {
	val yearNumber: String
	val dayNumber: String

	fun solve(input: String): Pair<Any?, Any?>

	fun solve(): Pair<Any?, Any?> = solve(readInput())

	private fun readInput(): String {
		return SystemFileSystem.source(Path("advent-of-code-$yearNumber/src/commonMain/resources/Day${dayNumber}.txt"))
			.buffered().readString()
	}
}
