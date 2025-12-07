import kotlinx.io.*
import kotlinx.io.files.*

abstract class Day {
	abstract fun solve(input: String): Pair<Solution?, Solution?>

	fun solve(): Pair<Solution?, Solution?> = solve(readInput())

	override fun toString(): String = this::class.simpleName!!.let { className ->
		val i = className.indexOfFirst(Char::isDigit)
		"${className.take(i)} ${className.drop(i)}"
	}

	private fun readInput(): String {
		return SystemFileSystem.source(Path("input/${this::class.simpleName}.txt")).buffered().readString()
	}
}
