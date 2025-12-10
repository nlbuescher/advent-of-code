package dev.buescher.adventofcode.core

import kotlinx.io.*
import kotlinx.io.files.*

abstract class Day(val year: String, val day: String) {
	abstract fun solve(input: String): Solutions

	fun solve(): Solutions = solve(readInput())

	private fun readInput(): String {
		return SystemFileSystem.source(Path("advent-of-code-$year/src/commonMain/resources/Day${day}.txt"))
			.buffered().readString().trim()
	}
}
