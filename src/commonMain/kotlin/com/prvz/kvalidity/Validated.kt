package com.prvz.kvalidity

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.prvz.kvalidity.constraint.ConstraintViolationException

public typealias ResultMonad<V, E> = com.github.michaelbull.result.Result<V, E>

public sealed class Validated<V>(
    public val value: V,
    public val violation: ConstraintViolationException?
) {

    public fun isValid(): Boolean = violation == null

    public fun isNotValid(): Boolean = !isValid()

    public fun throwIfIsNotValid(): V = if (isNotValid()) throw violation!! else value

    public fun toResult(): Result<V> =
        if (isValid()) Result.success(value) else Result.failure(violation!!)

    public class Impl<V> internal constructor(value: V, violation: ConstraintViolationException?) :
        Validated<V>(value = value, violation = violation)

    public class MappedImpl<FROM, TO>
    internal constructor(
        public val fromValue: FROM,
        value: TO,
        violation: ConstraintViolationException?
    ) : Validated<TO>(value = value, violation = violation) {}
}

public fun <T> Validated<T>.toResultMonad(): ResultMonad<T, ConstraintViolationException> =
    if (isValid()) Ok(value) else Err(violation!!)
