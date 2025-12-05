class Solution<T> private constructor(val value: T) {
    companion object {
        operator fun invoke(value: Byte) = Solution(value)
        operator fun invoke(value: Short) = Solution(value)
        operator fun invoke(value: Int) = Solution(value)
        operator fun invoke(value: Long) = Solution(value)
        operator fun invoke(value: UByte) = Solution(value)
        operator fun invoke(value: UShort) = Solution(value)
        operator fun invoke(value: UInt) = Solution(value)
        operator fun invoke(value: ULong) = Solution(value)
        operator fun invoke(value: String) = Solution(value)
    }
}
