package com.prvz.kvalidity.constraint

interface ConstraintViolation {
    val property: String
    val value: Any?
    val constraint: Constraint
}

data class DefaultConstraintViolation(
    override val property: String,
    override val value: Any? = null,
    override val constraint: Constraint
) : ConstraintViolation

/**
 * Represents a exception that contains all the violated constraints of an object
 *
 * @param constraintViolations specifies a set of violated constraints
 * @constructor creates a exception with the violated constraints
 *
 * @author Rodolpho S. Couto
 * @see ConstraintViolation
 * @since 0.1.0
 */
class ConstraintViolationException(val constraintViolations: Set<ConstraintViolation>) : RuntimeException()