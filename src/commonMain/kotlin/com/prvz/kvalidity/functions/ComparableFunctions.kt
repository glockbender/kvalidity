/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.prvz.kvalidity.functions

import com.prvz.kvalidity.Validator
import com.prvz.kvalidity.constraint.Between
import com.prvz.kvalidity.constraint.Constraint
import com.prvz.kvalidity.constraint.Greater
import com.prvz.kvalidity.constraint.Less
import com.prvz.kvalidity.constraint.LessOrEqual
import com.prvz.kvalidity.constraint.NotBetween

/** Validates if the [Comparable] property is less than another value */
public fun <T, V : Comparable<V>?> Validator<T>.Pipeline<V>.isLessThan(
    value: V,
    constraintFunc: (V) -> Constraint = { Less(value) }
): Validator<T>.Pipeline<V> = this.validate(constraintFunc) { it == null || it < value }

/** Validates if the [Comparable] property is less than or equal to another value */
public fun <T, V : Comparable<V>?> Validator<T>.Pipeline<V>.isLessThanOrEqualTo(
    value: V,
    constraintFunc: (V) -> Constraint = { LessOrEqual(value) }
): Validator<T>.Pipeline<V> = this.validate(constraintFunc) { it == null || it <= value }

/** Validates if the [Comparable] property is greater than another value */
public fun <T, V : Comparable<V>?> Validator<T>.Pipeline<V>.isGreaterThan(
    value: V,
    constraintFunc: (V?) -> Constraint = { Greater(value) }
): Validator<T>.Pipeline<V> = this.validate(constraintFunc) { it == null || it > value }

/** Validates if the [Comparable] property is greater than or equal to another value */
public fun <T, V : Comparable<V>?> Validator<T>.Pipeline<V>.isGreaterThanOrEqualTo(
    value: V,
    constraintFunc: (V?) -> Constraint = { Greater(value) }
): Validator<T>.Pipeline<V> = this.validate(constraintFunc) { it == null || it >= value }

/**
 * Validates if the [Comparable] property is between two values
 *
 * @param start (inclusively) specifies value that should start
 * @param end (inclusively) specifies value that should end
 */
public fun <T, V : Comparable<V>?> Validator<T>.Pipeline<V>.isBetween(
    start: V & Any,
    end: V & Any,
    constraintFunc: (V?) -> Constraint = { Between(start, end) }
): Validator<T>.Pipeline<V> =
    this.validate(constraintFunc) { it == null || it in start.rangeTo(end) }

/**
 * Validates if the [Comparable] property isn't between two values
 *
 * @param start (inclusively) specifies value that shouldn't start
 * @param end (inclusively) specifies value that shouldn't end
 */
public fun <T, V : Comparable<V>?> Validator<T>.Pipeline<V>.isNotBetween(
    start: V & Any,
    end: V & Any,
    constraintFunc: (V?) -> Constraint = { NotBetween(start, end) }
): Validator<T>.Pipeline<V> =
    this.validate(constraintFunc) { it == null || it !in start.rangeTo(end) }
