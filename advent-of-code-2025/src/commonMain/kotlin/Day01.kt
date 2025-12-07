object Day01 : Day() {
	private const val DIAL_START = 50
	private const val DIAL_SIZE = 100

	private fun processInput(input: String): List<Int> {
		return input
			.lines()
			.filter(String::isNotEmpty)
			.map {
				if (it.startsWith('L')) {
					it.substringAfter("L").toInt().unaryMinus()
				}
				else if (it.startsWith('R')) {
					it.substringAfter("R").toInt()
				}
				else error("Invalid input: '$it'. Expected 'L' or 'R' prefix.")
			}
	}

	override fun solve(input: String): Pair<Solution?, Solution?> {
		var solution1 = 0
		var solution2 = 0

		processInput(input).fold(DIAL_START) { lastPosition, rotation ->
			val nextPosition = lastPosition + rotation

			if (nextPosition.mod(DIAL_SIZE) == 0) {
				solution1 += 1
			}

			if (rotation > 0) {
				solution2 += nextPosition / DIAL_SIZE
			}
			else {
				if (lastPosition == 0) {
					solution2 -= 1
				}

				solution2 += (DIAL_SIZE - nextPosition) / DIAL_SIZE
			}

			nextPosition.mod(100)
		}

		return Solution.of(solution1) to Solution.of(solution2)
	}
}
