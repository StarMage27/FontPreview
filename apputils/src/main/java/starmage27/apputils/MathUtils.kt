package starmage27.apputils

import kotlin.math.abs

object MathUtils {
    fun Float.roughlyEquals(other: Float, tolerance: Float = 0.001f): Boolean {
        return this.toBits() == other.toBits() || abs(this - other) <= tolerance
    }
}