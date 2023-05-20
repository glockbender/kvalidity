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

import com.prvz.kvalidity.ObjectValidation
import com.prvz.kvalidity.constraint.*

/** Validates if the [Comparable] property is less than another value */
public fun <V : Comparable<V>?> ObjectValidation<V>.isLessThan(value: V): ObjectValidation<V> =
    this.validate({ Less(value) }) { it == null || it < value }

/** Validates if the [Comparable] property is less than or equal to another value */
public fun <V : Comparable<V>?> ObjectValidation<V>.isLessThanOrEqualTo(
    value: V
): ObjectValidation<V> = this.validate({ LessOrEqual(value) }) { it == null || it <= value }

/** Validates if the [Comparable] property is greater than another value */
public fun <V : Comparable<V>?> ObjectValidation<V>.isGreaterThan(value: V): ObjectValidation<V> =
    this.validate({ Greater(value) }) { it == null || it > value }

/** Validates if the [Comparable] property is greater than or equal to another value */
public fun <V : Comparable<V>?> ObjectValidation<V>.isGreaterThanOrEqualTo(
    value: V
): ObjectValidation<V> = this.validate({ Greater(value) }) { it == null || it >= value }

/**
 * Validates if the [Comparable] property is between two values
 *
 * @param start (inclusively) specifies value that should start
 * @param end (inclusively) specifies value that should end
 */
public fun <V : Comparable<V>?> ObjectValidation<V>.isBetween(
    start: V & Any,
    end: V & Any
): ObjectValidation<V> =
    this.validate({ Between(start, end) }) { it == null || it in start.rangeTo(end) }

/**
 * Validates if the [Comparable] property isn't between two values
 *
 * @param start (inclusively) specifies value that shouldn't start
 * @param end (inclusively) specifies value that shouldn't end
 */
public fun <V : Comparable<V>?> ObjectValidation<V>.isNotBetween(
    start: V & Any,
    end: V & Any
): ObjectValidation<V> =
    this.validate({ NotBetween(start, end) }) { it == null || it !in start.rangeTo(end) }
