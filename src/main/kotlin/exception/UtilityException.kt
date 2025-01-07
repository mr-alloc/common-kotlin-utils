package exception

import constant.ErrorCode

class UtilityException (
    val origin: Throwable,
    override val message: String,
    val errorCode: ErrorCode?
): RuntimeException(message) {

    constructor(origin: Throwable): this(origin, origin.message ?: NO_MESSAGE_FOR_UTIL_EXCEPTION, null) {
    }

    companion object {
        const val NO_MESSAGE_FOR_UTIL_EXCEPTION = "No message for UtilityException"
    }
}