package com.prvz.kvalidity

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.prvz.kvalidity.constraint.ConstraintViolationException

typealias ResultMonad<V, E> = com.github.michaelbull.result.Result<V, E>

sealed class Validated<V>(val value: V, val violation: ConstraintViolationException?) {

    fun isValid(): Boolean = violation == null

    fun isNotValid(): Boolean = !isValid()

    fun throwIfIsNotValid(): V = if (isNotValid()) throw violation!! else value

    fun toResult(): Result<V> =
        if (isValid()) Result.success(value) else Result.failure(violation!!)

    class Impl<V> internal constructor(value: V, violation: ConstraintViolationException?) :
        Validated<V>(value = value, violation = violation)

    class MappedImpl<FROM, TO>
    internal constructor(val fromValue: FROM, value: TO, violation: ConstraintViolationException?) :
        Validated<TO>(value = value, violation = violation) {}
}

fun <T> Validated<T>.toResultMonad(): ResultMonad<T, ConstraintViolationException> =
    if (isValid()) Ok(value) else Err(violation!!)
