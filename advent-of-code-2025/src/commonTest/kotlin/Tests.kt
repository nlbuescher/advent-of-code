package dev.buescher.adventofcode.mmxxv

import dev.buescher.adventofcode.core.Solutions
import kotlin.test.*

class Tests {
	fun test(day: Int, expected: Solutions, testInput: String) {
		val actual = getDay(day).solve(testInput)

		assertEquals(expected, actual)
	}

	@Test
	fun day01() = test(
		day = 1,
		expected = Solutions(part1 = 3, part2 = 6),
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

	@Test
	fun day02() = test(
		day = 2,
		expected = Solutions(part1 = 1227775554, part2 = 4174379265),
		testInput = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124",
	)

	@Test
	fun day03() = test(
		day = 3,
		expected = Solutions(part1 = 357, part2 = 3121910778619),
		testInput = """
			987654321111111
			811111111111119
			234234234234278
			818181911112111
		""".trimIndent(),
	)
}
