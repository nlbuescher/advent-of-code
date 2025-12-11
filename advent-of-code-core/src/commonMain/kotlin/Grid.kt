package dev.buescher.adventofcode.core

import kotlin.math.*

class Grid private constructor(
	private val data: List<String>,
	private val startX: Int,
	private val startY: Int,
	private val endX: Int,
	private val endY: Int,
) : Iterable<Grid.Cell> {
	val width: Int get() = endX - startX
	val height: Int get() = endY - startY

	constructor(data: List<String>) : this(
		data,
		startX = 0,
		startY = 0,
		endX = if (data.isNotEmpty()) data.first().length else 0,
		endY = data.size,
	) {
		require(data.all { it.length == width }) {
			"All rows in the grid must have the same width ($width)"
		}
	}

	operator fun get(x: Int, y: Int): Cell {
		checkBounds(x, y, width, height)
		return Cell(this, x, y)
	}

	fun getValue(x: Int, y: Int): Char = data[startY + y][startX + x]

	fun rows(): Sequence<Sequence<Cell>> {
		return (0 until height).asSequence().map { y ->
			(0 until width).asSequence().map { x -> get(x, y) }
		}
	}

	fun columns(): Sequence<Sequence<Cell>> {
		return (0 until width).asSequence().map { x ->
			(0 until height).asSequence().map { y -> get(x, y) }
		}
	}

	override fun iterator(): Iterator<Cell> = rows().flatten().iterator()

	override fun hashCode(): Int = data.hashCode()

	override fun equals(other: Any?): Boolean = other is Grid && this.data == other.data

	override fun toString() = "(($startX, $startY)\n ($endX, $endY))"

	class Cell internal constructor(private val grid: Grid, val x: Int, val y: Int) {
		val value: Char
			get() {
				return grid.data[grid.startY + y][grid.startX + x]
			}

		fun neighbors(): Sequence<Cell> {
			val startX = max(0, x - 1)
			val startY = max(0, y - 1)
			val endX = min(grid.width, x + 2)
			val endY = min(grid.height, y + 2)

			val xRange = startX until endX
			val yRange = startY until endY

			return xRange.asSequence()
				.flatMap { x -> yRange.asSequence().map { y -> x to y } }
				.filter { (x, y) -> x != this.x || y != this.y }
				.map { (x, y) -> Cell(grid, x, y) }
		}

		override fun hashCode(): Int {
			var result = grid.hashCode()
			result = 31 * result + x.hashCode()
			result = 31 * result + y.hashCode()
			return result
		}

		override fun equals(other: Any?) =
			other is Cell
				&& grid == other.grid
				&& x == other.x
				&& y == other.y

		override fun toString() = "($x, $y)"
	}
}

private fun checkBounds(x: Int, y: Int, width: Int, height: Int) {
	if (width == 0 || height == 0) {
		throw IndexOutOfBoundsException("Grid is empty")
	}
	if (x !in 0 until width || y !in 0 until height) {
		throw IndexOutOfBoundsException("Position ($x, $y) is outside of the grid ($width, $height)")
	}
}
