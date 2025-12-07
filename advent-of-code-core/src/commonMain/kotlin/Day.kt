import kotlinx.io.*
import kotlinx.io.files.*

interface Day {
	fun solve(): Pair<Solution?, Solution?> = solve(readInput())

	fun solve(input: String): Pair<Solution?, Solution?>

	private fun readInput(): String {
		return SystemFileSystem.source(Path("input/${this::class.simpleName}.txt")).buffered().readString()
	}
}
