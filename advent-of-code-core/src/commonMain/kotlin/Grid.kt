@file:Suppress("unused")

package dev.buescher.adventofcode.core

import kotlin.math.*

data class Point(val x: Int, val y: Int)

class Grid private constructor(
	private val data: List<CharArray>,
	private val start: Point,
	private val end: Point,
) : Iterable<Grid.Cell> {
	val width: Int get() = end.x - start.x
	val height: Int get() = end.y - start.y

	constructor(data: List<String>) : this(
		data.map(String::toCharArray),
		start = Point(x = 0, y = 0),
		end = Point(
			x = if (data.isNotEmpty()) data.first().length else 0,
			y = data.size,
		),
	) {
		require(data.all { it.length == width }) {
			"All rows in the grid must have the same width ($width)"
		}
	}

	operator fun get(x: Int, y: Int): Char = data[start.y + y][start.x + x]
	operator fun set(x: Int, y: Int, value: Char) = data[start.y + y].set(start.x + x, value)

	fun getCell(x: Int, y: Int): Cell {
		checkBounds(x, y, width, height)
		return Cell(this, x, y)
	}

	fun getRow(row: Int): Row {
		checkBounds(0, row, width, height)
		return Row((0 until width).asSequence().map { column -> getCell(column, row) })
	}

	fun getColumn(column: Int): Column {
		checkBounds(column, 0, width, height)
		return Column((0 until height).asSequence().map { row -> getCell(column, row) })
	}

	override fun iterator(): Iterator<Cell> = cells().iterator()

	fun cells(): Sequence<Cell> = rows().flatten()

	class Row internal constructor(row: Sequence<Cell>) : Sequence<Cell> by row

	fun rows(): Sequence<Row> {
		return (0 until height).asSequence().map { y ->
			(0 until width).asSequence().map { x -> getCell(x, y) }.let(::Row)
		}
	}

	class Column internal constructor(column: Sequence<Cell>) : Sequence<Cell> by column

	fun columns(): Sequence<Column> {
		return (0 until width).asSequence().map { x ->
			(0 until height).asSequence().map { y -> getCell(x, y) }.let(::Column)
		}
	}

	override fun hashCode(): Int = data.hashCode()

	override fun equals(other: Any?): Boolean = other is Grid && this.data == other.data

	class Cell internal constructor(private val grid: Grid, val x: Int, val y: Int) {
		var value: Char
			get() = grid[x, y]
			set(value) = grid.set(x, y, value)

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

		override fun hashCode(): Int = value.hashCode()

		override fun equals(other: Any?) = other is Cell && value == other.value

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
