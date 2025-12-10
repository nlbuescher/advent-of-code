package dev.buescher.adventofcode.core

class Solutions(val part1: Any?, val part2: Any?) {
	operator fun component1() = part1
	operator fun component2() = part2

	override fun hashCode(): Int = 31 * part1.hashCode() + part2.hashCode()

	override fun equals(other: Any?): Boolean = other is Solutions && this.toString() == other.toString()

	override fun toString(): String = "($part1, $part2)"
}
