@file:Suppress("unused")

package dev.buescher.adventofcode.core

import dev.buescher.adventofcode.core.Grid.Column.Order.*
import dev.buescher.adventofcode.core.Grid.Row.Order.*

data class Point(val x: Int, val y: Int)

class Grid private constructor(
	private val data: List<CharArray>,
	private val start: Point,
	private val end: Point,
) {
	val width: Int get() = end.x - start.x
	val height: Int get() = end.y - start.y

	constructor(data: List<CharSequence>) : this(
		data.map { it.toList().toCharArray() },
		start = Point(
			x = 0,
			y = 0,
		),
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

	class Row internal constructor(row: Sequence<Cell>) : Sequence<Cell> by row {
		enum class Order { LeftToRight, RightToLeft }
	}

	private val rowIndicesTTB = 0 until height
	private val rowIndicesBTT = height - 1 downTo 0

	fun getRowIndices(columnOrder: Column.Order = TopToBottom): IntProgression = when (columnOrder) {
		TopToBottom -> rowIndicesTTB
		BottomToTop -> rowIndicesBTT
	}

	fun getRow(row: Int, rowOrder: Row.Order = LeftToRight): Row {
		checkBounds(0, row, width, height)
		return Row(getColumnIndices(rowOrder).asSequence().map { x -> Cell(this, x, row) })
	}

	fun rows(rowOrder: Row.Order = LeftToRight, columnOrder: Column.Order = TopToBottom): Sequence<Row> {
		return getRowIndices(columnOrder).asSequence().map { y ->
			Row(
				getColumnIndices(rowOrder).asSequence().map { x ->
					Cell(this, x, y)
				},
			)
		}
	}

	class Column internal constructor(column: Sequence<Cell>) : Sequence<Cell> by column {
		enum class Order { TopToBottom, BottomToTop }
	}

	private val columnIndicesLTR = 0 until width
	private val columnIndicesRTL = width - 1 downTo 0

	fun getColumnIndices(rowOrder: Row.Order = LeftToRight): IntProgression = when (rowOrder) {
		LeftToRight -> columnIndicesLTR
		RightToLeft -> columnIndicesRTL
	}

	fun getColumn(column: Int, order: Column.Order): Column {
		checkBounds(column, 0, width, height)
		return Column(getRowIndices(order).asSequence().map { row -> Cell(this, column, row) })
	}

	fun columns(columnOrder: Column.Order = TopToBottom, rowOrder: Row.Order = LeftToRight): Sequence<Column> {
		return getColumnIndices(rowOrder).asSequence().map { x ->
			Column(
				getRowIndices(columnOrder).asSequence().map { y ->
					Cell(this, x, y)
				},
			)
		}
	}

	override fun hashCode(): Int = data.hashCode()

	override fun equals(other: Any?): Boolean =
		other is Grid && this.data.withIndex().all { (i, it) -> it.contentEquals(other.data[i]) }

	class Cell internal constructor(private val grid: Grid, val x: Int, val y: Int) {
		var value: Char
			get() = grid[x, y]
			set(value) = grid.set(x, y, value)

		fun neighbors(): Sequence<Cell> {
			val xRange = (x - 1).coerceAtLeast(0) until (x + 2).coerceAtMost(grid.width)
			val yRange = (y - 1).coerceAtLeast(0) until (y + 2).coerceAtMost(grid.height)

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
