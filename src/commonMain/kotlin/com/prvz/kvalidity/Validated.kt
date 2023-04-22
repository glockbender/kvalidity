package com.prvz.kvalidity

import com.prvz.kvalidity.constraint.ConstraintViolationException

sealed interface Validated<T> {

    fun isValid(): Boolean

    fun isNotValid(): Boolean = !isValid()

    fun getViolationOrNull(): ConstraintViolationException?

    fun throwIfIsNotValid(): T

    fun toResult(): Result<T>
}

class ValidatedImpl<T>
private constructor(private val value: T, private val violation: ConstraintViolationException?) :
    Validated<T> {

    override fun isValid(): Boolean = violation == null

    override fun getViolationOrNull(): ConstraintViolationException? = violation

    override fun throwIfIsNotValid(): T = if (isNotValid()) throw violation!! else value

    override fun toResult(): Result<T> {
        TODO("Not yet implemented")
    }
}

class ValidatedMapped<FROM, TO> : Validated<TO> {}
