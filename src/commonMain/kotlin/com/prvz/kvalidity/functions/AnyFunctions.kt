/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.prvz.kvalidity.functions

import com.prvz.kvalidity.MutatedObjectValidation
import com.prvz.kvalidity.ObjectValidation
import com.prvz.kvalidity.constraint.Equals
import com.prvz.kvalidity.constraint.In
import com.prvz.kvalidity.constraint.NotEquals
import com.prvz.kvalidity.constraint.NotIn
import com.prvz.kvalidity.constraint.NotNull
import com.prvz.kvalidity.constraint.Null
import com.prvz.kvalidity.constraint.Valid
import com.prvz.kvalidity.constraint.model.Constraint

/**
 * Validates if the property value is null
 *
 * @return the same receiver property
 * @receiver the property to be validated
 */
public fun <V> ObjectValidation<V>.isNull(): ObjectValidation<V> =
    this.validate({ Null }) { it == null }

/** Validates if the property value is not null */
public fun <V> ObjectValidation<V?>.isNotNull(
    constraintFunc: (V?) -> Constraint = { NotNull }
): MutatedObjectValidation<V> =
    this.validateAndMap(
        constraintBuilder = constraintFunc,
        isValid = { it != null },
        mapperFunc = { it ?: throw IllegalStateException("Unexpected null in mapper func") })

/**
 * Validates if the property value is equal to another value
 *
 * @param value specifies the value that should be equal
 */
public fun <V> ObjectValidation<V>.isEqualTo(value: V): ObjectValidation<V> =
    this.validate({ Equals(value) }) { it == null || it == value }

/**
 * Validates if the property value isn't equal to another value
 *
 * @param value specifies the value that should not be equal
 */
public fun <V> ObjectValidation<V>.isNotEqualTo(value: V): ObjectValidation<V> =
    this.validate({ NotEquals(value) }) { it == null || it != value }

/**
 * Validates if the property value is equal to one of the values
 *
 * @param values specifies the array of values to be compared
 */
public fun <V> ObjectValidation<V>.isIn(vararg values: V): ObjectValidation<V> =
    this.validate({ In(values.toSet()) }) { it == null || values.contains(it) }

/**
 * Validates if the property value is equal to one of the values
 *
 * @param values specifies the iterable of values to be compared
 */
public fun <V> ObjectValidation<V>.isIn(values: Iterable<V>): ObjectValidation<V> =
    this.validate({ In(values) }) { it == null || values.contains(it) }

/**
 * Validates if the property value isn't equal to any value
 *
 * @param values specifies the array of values to be compared
 */
public fun <V> ObjectValidation<V>.isNotIn(vararg values: V): ObjectValidation<V> =
    this.validate({ NotIn(values.toSet()) }) { it == null || !values.contains(it) }

/**
 * Validates if the property value isn't equal to any value
 *
 * @param values specifies the iterable of values to be compared
 */
public fun <V> ObjectValidation<V>.isNotIn(values: Iterable<V>): ObjectValidation<V> =
    this.validate({ NotIn(values) }) { it == null || !values.contains(it) }

/**
 * Validates if the property is valid by passing a custom function
 *
 * @param validator specifies the validation function
 */
public fun <V> ObjectValidation<V>.isValid(validator: (V) -> Boolean): ObjectValidation<V> =
    this.validate({ Valid }) { it == null || validator(it) }

/**
 * Validates if the property is valid by passing a custom suspending function
 *
 * @param validator specifies the validation function
 */
public suspend fun <V> ObjectValidation<V>.isCoValid(
    validator: suspend (V) -> Boolean
): ObjectValidation<V> = this.coValidate(Valid) { it == null || validator(it) }
