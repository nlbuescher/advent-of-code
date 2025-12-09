package dev.buescher.adventofcode.mmxxv

import kotlin.test.*

class Tests {
	fun test(day: Int, expected: Pair<Any?, Any?>, testInput: String) {
		val actual = getDay(day).solve(testInput)

		assertEquals(expected, actual)
	}

	@Test
	fun day01() = test(
		day = 1,
		expected = 3 to 6,
		testInput = """
			L68
			L30
			R48
			L5
			R60
			L55
			L1
			L99
			R14
			L82
		""".trimIndent(),
	)
}
