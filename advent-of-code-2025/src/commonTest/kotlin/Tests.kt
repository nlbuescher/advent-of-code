import kotlin.test.*

class Tests {
	fun test(day: Int, expected: Pair<Solution?, Solution?>, input: String) {
		val actual = getDay(day).solve(input)

		assertEquals(expected, actual)
	}

	@Test
	fun day01() = test(
		day = 1, expected = Solution.of(3) to null, input = """
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
		""".trimIndent()
	)
}
