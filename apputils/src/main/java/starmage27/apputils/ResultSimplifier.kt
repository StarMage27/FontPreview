package starmage27.apputils

import android.util.Log
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success

@Suppress("NOTHING_TO_INLINE", "FunctionName")
object ResultSimplifier {
    inline fun <reified T> Ok(result: T): Result<T> = success(result)
    inline fun Ok(): Result<Unit> = success(Unit)
    inline fun <reified T> Err(e: Throwable): Result<T> = failure(e)
    inline fun <reified T> Err(message: String): Result<T> = failure(Exception(message))
    inline fun Result<Unit>.logOnFailure(tag: String = "Failure") {
        onFailure { e -> Log.e(tag, e.message, e) }
    }
}