package com.prvz.kvalidity.constraint

public interface ConstraintViolation {
    public val property: String?
    public val value: Any?
    public val constraint: Constraint
}

public data class DefaultConstraintViolation(
    override val property: String?,
    override val value: Any? = null,
    override val constraint: Constraint
) : ConstraintViolation

/** Represents an exception that contains all the violated constraints of an object */
public class ConstraintViolationException(
    public val constraintViolations: Collection<ConstraintViolation>
) : RuntimeException()
