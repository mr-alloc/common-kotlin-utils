@file:JvmName("EncryptUtil")
package utils

import constant.EncryptAlgorithm
import exception.UtilityException
import java.security.MessageDigest

private val HEX_CHARS = "0123456789abcdef".toCharArray()


fun String.encrypt(algorithm: EncryptAlgorithm): String = runCatching {
    MessageDigest.getInstance(algorithm.value)
        .digest(toByteArray())
        .toHex()
}.getOrElse { throw UtilityException(it) }

private fun ByteArray.toHex(): String = buildString(size * 2) {
    for (byte in this@toHex) {
        val i = byte.toInt()
        append(HEX_CHARS[(i shr 4) and 0x0f])
        append(HEX_CHARS[i and 0x0f])
    }
}
