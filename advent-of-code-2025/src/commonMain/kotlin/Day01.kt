object Day01 : Day {
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

		processInput(input)
			.fold(50) { position, rotation ->
				(position + rotation).mod(100).also { newPosition ->
					if (newPosition == 0) {
						solution1 += 1
					}
				}
			}

		return Solution.of(solution1) to null
	}
}
