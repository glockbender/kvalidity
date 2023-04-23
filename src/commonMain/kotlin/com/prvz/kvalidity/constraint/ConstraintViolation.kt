package com.prvz.kvalidity.constraint

interface ConstraintViolation {
    val property: String?
    val value: Any?
    val constraint: Constraint
}

data class DefaultConstraintViolation(
    override val property: String?,
    override val value: Any? = null,
    override val constraint: Constraint
) : ConstraintViolation

/** Represents an exception that contains all the violated constraints of an object */
class ConstraintViolationException(val constraintViolations: Collection<ConstraintViolation>) :
    RuntimeException()
