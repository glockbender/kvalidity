package com.prvz.kvalidity.constraint

public interface Constraint {
    public val messageProvider: ConstraintMessageProvider

    public companion object {
        public inline fun Any?.toParamArrayOrNull(): Array<out Any>? =
            if (this != null) {
                arrayOf(this)
            } else null

        public inline fun Pair<Any?, Any?>.toParamArrayOrNull(): Array<out Any>? {
            val (first, second) = this
            return if (first == null || second == null) {
                null
            } else {
                arrayOf(first, second)
            }
        }
    }
}
