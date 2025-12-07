sealed class Solution {
	abstract val value: Any

	companion object {
		fun of(value: Byte): Solution = ByteSolution(value)
		fun of(value: Short): Solution = ShortSolution(value)
		fun of(value: Int): Solution = IntSolution(value)
		fun of(value: Long): Solution = LongSolution(value)
		fun of(value: UByte): Solution = UByteSolution(value)
		fun of(value: UShort): Solution = UShortSolution(value)
		fun of(value: UInt): Solution = UIntSolution(value)
		fun of(value: ULong): Solution = ULongSolution(value)
		fun of(value: String): Solution = StringSolution(value)
	}

	override fun hashCode(): Int = value.hashCode()

	override fun equals(other: Any?): Boolean = other is Solution && value == other.value

	override fun toString(): String = value.toString()
}

class ByteSolution internal constructor(override val value: Byte) : Solution()
class ShortSolution internal constructor(override val value: Short) : Solution()
class IntSolution internal constructor(override val value: Int) : Solution()
class LongSolution internal constructor(override val value: Long) : Solution()
class UByteSolution internal constructor(override val value: UByte) : Solution()
class UShortSolution internal constructor(override val value: UShort) : Solution()
class UIntSolution internal constructor(override val value: UInt) : Solution()
class ULongSolution internal constructor(override val value: ULong) : Solution()
class StringSolution internal constructor(override val value: String) : Solution()
