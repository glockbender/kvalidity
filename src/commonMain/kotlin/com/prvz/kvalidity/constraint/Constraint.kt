package com.prvz.kvalidity.constraint

interface Constraint {
    val messageProvider: ConstraintMessageProvider

    companion object {
        inline fun Any?.toParamArrayOrNull(): Array<out Any>? =
            if (this != null) {
                arrayOf(this)
            } else null

        inline fun Pair<Any?, Any?>.toParamArrayOrNull(): Array<out Any>? {
            val (first, second) = this
            return if (first == null || second == null) {
                null
            } else {
                arrayOf(first, second)
            }
        }
    }
}
