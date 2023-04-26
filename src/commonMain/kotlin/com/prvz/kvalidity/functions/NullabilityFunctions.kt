package com.prvz.kvalidity.functions

import com.prvz.kvalidity.Validator
import com.prvz.kvalidity.constraint.Constraint
import com.prvz.kvalidity.constraint.IsNotNull

public fun <T, V> Validator<T>.Pipeline<V?>.isNotNull(
    constraintFunc: (V?) -> Constraint = { IsNotNull }
): Validator<T>.PipelineMutated<V> =
    this.validateAndMap(
        constraintFunc = constraintFunc, isValid = { it != null }, mapperFunc = { it!! })
