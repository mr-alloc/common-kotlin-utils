package utils

import java.util.concurrent.ThreadLocalRandom
import java.util.function.IntPredicate


private const val ASCII_NUMBER_START = 48
private const val ASCII_NUMBER_END = 57
private const val ASCII_LOWER_START = 97
private const val ASCII_LOWER_F = 102
private const val ASCII_LOWER_END = 122

object RandomUtil {

    private val NUMBER_RANGE = IntPredicate { i: Int -> i in ASCII_NUMBER_START..ASCII_NUMBER_END }
    private val LOWER_CHARACTER_RANGE = IntPredicate { i: Int -> i in ASCII_LOWER_START..ASCII_LOWER_END }
    private val LOWER_HAX_CHARACTER_RANGE = IntPredicate { i: Int -> i in ASCII_LOWER_START..ASCII_LOWER_F }

    private val HEX_COLOR_RANGE = IntPredicate { i: Int -> NUMBER_RANGE.test(i) || LOWER_HAX_CHARACTER_RANGE.test(i) }


    fun makeLower(length: Long): String {
        return ThreadLocalRandom.current()
            .ints(ASCII_NUMBER_START, ASCII_LOWER_F + 1)
            .filter(LOWER_CHARACTER_RANGE)
            .limit(length)
            .collect(
                { StringBuilder() },
                { obj: StringBuilder, codePoint: Int -> obj.appendCodePoint(codePoint) },
                { obj: StringBuilder, s: StringBuilder? -> obj.append(s) })
            .toString()
    }

    fun makeNumeric(length: Long): String {
        return ThreadLocalRandom.current()
            .ints(ASCII_NUMBER_START, ASCII_NUMBER_END + 1)
            .limit(length)
            .collect(
                { StringBuilder() },
                { obj: StringBuilder, codePoint: Int -> obj.appendCodePoint(codePoint) },
                { obj: StringBuilder, s: StringBuilder? -> obj.append(s) })
            .toString()
    }

    fun makeNumericLower(length: Long): String {
        return ThreadLocalRandom.current()
            .ints(ASCII_NUMBER_START, ASCII_LOWER_END + 1)
            .filter(NUMBER_RANGE.or(LOWER_CHARACTER_RANGE))
            .limit(length)
            .collect(
                { StringBuilder() },
                { obj: StringBuilder, codePoint: Int -> obj.appendCodePoint(codePoint) },
                { obj: StringBuilder, s: StringBuilder? -> obj.append(s) })
            .toString()
    }

    fun makeRandomHexColor(): String {
        return ThreadLocalRandom.current()
            .ints(ASCII_NUMBER_START, ASCII_LOWER_F + 1)
            .filter(HEX_COLOR_RANGE)
            .limit(6)
            .collect(
                { StringBuilder() },
                { obj: StringBuilder, codePoint: Int -> obj.appendCodePoint(codePoint) },
                { obj: StringBuilder, s: StringBuilder? -> obj.append(s) })
            .toString()
    }
}
