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

/** Validates if the [String] property is empty */
public fun <V : String?> ObjectValidation<V>.isEmpty(): ObjectValidation<V> =
    this.validate({ Empty }) { it.isNullOrEmpty() }

/** Validates if the [String] property is not empty */
public fun <V : String?> ObjectValidation<V>.isNotEmpty(): ObjectValidation<V> =
    this.validate({ NotEmpty }) { it == null || it.isNotEmpty() }

/** Validates if the [String] property is blank */
public fun <V : String?> ObjectValidation<V>.isBlank(): ObjectValidation<V> =
    this.validate({ Blank }) { it == null || it.isBlank() }

/** Validates if the [String] property is not blank */
public fun <V : String?> ObjectValidation<V>.isNotBlank(): ObjectValidation<V> =
    this.validate({ NotBlank }) { it == null || it.isNotBlank() }

/**
 * Validates if the property value is equal to another value ignoring case sensitive
 *
 * @param value specifies the value that should be equal
 */
public fun <V : String?> ObjectValidation<V>.isEqualToIgnoringCase(
    value: String
): ObjectValidation<V> =
    this.validate({ Equals(value) }) { it == null || it.equals(other = value, ignoreCase = true) }

/**
 * Validates if the property value isn't equal to another value ignoring case sensitive
 *
 * @param value specifies the value that should not be equal
 */
public fun <V : String?> ObjectValidation<V>.isNotEqualToIgnoringCase(
    value: String
): ObjectValidation<V> =
    this.validate({ NotEquals(value) }) {
        it == null || !it.equals(other = value, ignoreCase = true)
    }

/**
 * Validates if the property value is equal to one of the values ignoring case sensitive
 *
 * @param values specifies the array of values to be compared
 */
public fun <V : String?> ObjectValidation<V>.isInIgnoringCase(
    vararg values: String
): ObjectValidation<V> =
    this.validate({ In(values.toSet()) }) {
        it == null || values.toSet().any { e -> it.equals(other = e, ignoreCase = true) }
    }

/**
 * Validates if the property value is equal to one of the values ignoring case sensitive
 *
 * @param values specifies the iterable of values to be compared
 */
public fun <V : String?> ObjectValidation<V>.isInIgnoringCase(
    values: Iterable<String>
): ObjectValidation<V> =
    this.validate({ In(values) }) {
        it == null || values.any { e -> it.equals(other = e, ignoreCase = true) }
    }

/**
 * Validates if the property value isn't equal to any value ignoring case sensitive
 *
 * @param values specifies the array of values to be compared
 */
public fun <V : String?> ObjectValidation<V>.isNotInIgnoringCase(
    vararg values: String
): ObjectValidation<V> =
    this.validate({ NotIn(values.toSet()) }) {
        it == null || values.toSet().none { e -> it.equals(other = e, ignoreCase = true) }
    }

/**
 * Validates if the property value isn't equal to any value ignoring case sensitive
 *
 * @param values specifies the iterable of values to be compared
 */
public fun <V : String?> ObjectValidation<V>.isNotInIgnoringCase(
    values: Iterable<String>
): ObjectValidation<V> =
    this.validate({ NotIn(values) }) {
        it == null || values.none { e -> it.equals(other = e, ignoreCase = true) }
    }

/**
 * Validates if the [String] property length is within the limits (min and max)
 *
 * @param min specifies the minimum size
 * @param max specifies the maximum size
 */
public fun <V : String?> ObjectValidation<V>.hasSize(
    min: Int = Int.MIN_VALUE,
    max: Int = Int.MAX_VALUE
): ObjectValidation<V> =
    this.validate({ Size(min, max) }) { it == null || it.length in min.rangeTo(max) }

/**
 * Validates if the [String] property contains the value
 *
 * @param value specifies the value that should contain
 */
public fun <V : String?> ObjectValidation<V>.contains(value: String): ObjectValidation<V> =
    this.validate({ Contains(value) }) { it == null || it.contains(value) }

/**
 * Validates if the [String] property contains the value ignoring case sensitive
 *
 * @param value specifies the value that should contain
 */
public fun <V : String?> ObjectValidation<V>.containsIgnoringCase(
    value: String
): ObjectValidation<V> =
    this.validate({ Contains(value) }) {
        it == null || it.contains(other = value, ignoreCase = true)
    }

/**
 * Validates if the [String] property contains all values
 *
 * @param values specifies the all values that should contain
 */
public fun <V : String?> ObjectValidation<V>.containsAll(
    vararg values: String
): ObjectValidation<V> =
    this.validate({ ContainsAll(values.toSet()) }) {
        it == null || values.toSet().all { e -> it.contains(e) }
    }

/**
 * Validates if the [String] property contains all values
 *
 * @param values specifies the all values that should contain
 */
public fun <V : String?> ObjectValidation<V>.containsAll(
    values: Iterable<String>
): ObjectValidation<V> =
    this.validate({ ContainsAll(values) }) { it == null || values.all { e -> it.contains(e) } }

/**
 * Validates if the [String] property contains all values ignoring case sensitive
 *
 * @param values specifies the all values that should contain
 */
public fun <V : String?> ObjectValidation<V>.containsAllIgnoringCase(
    vararg values: String
): ObjectValidation<V> =
    this.validate({ ContainsAll(values.toSet()) }) {
        it == null || values.toSet().all { e -> it.contains(other = e, ignoreCase = true) }
    }

/**
 * Validates if the [String] property contains all values ignoring case sensitive
 *
 * @param values specifies the all values that should contain
 */
public fun <V : String?> ObjectValidation<V>.containsAllIgnoringCase(
    values: Iterable<String>
): ObjectValidation<V> =
    this.validate({ ContainsAll(values) }) {
        it == null || values.all { e -> it.contains(other = e, ignoreCase = true) }
    }

/**
 * Validates if the [String] property contains any value
 *
 * @param values specifies the values that one should contain
 */
public fun <V : String?> ObjectValidation<V>.containsAny(
    vararg values: String
): ObjectValidation<V> =
    this.validate({ ContainsAny(values.toSet()) }) {
        it == null || values.toSet().any { e -> it.contains(e) }
    }

/**
 * Validates if the [String] property contains any value
 *
 * @param values specifies the values that one should contain
 */
public fun <V : String?> ObjectValidation<V>.containsAny(
    values: Iterable<String>
): ObjectValidation<V> =
    this.validate({ ContainsAny(values) }) { it == null || values.any { e -> it.contains(e) } }

/**
 * Validates if the [String] property contains any value ignoring case sensitive
 *
 * @param values specifies the values that one should contain
 */
public fun <V : String?> ObjectValidation<V>.containsAnyIgnoringCase(
    vararg values: String
): ObjectValidation<V> =
    this.validate({ ContainsAny(values.toSet()) }) {
        it == null || values.toSet().any { e -> it.contains(other = e, ignoreCase = true) }
    }

/**
 * Validates if the [String] property contains any value ignoring case sensitive
 *
 * @param values specifies the values that one should contain
 */
public fun <V : String?> ObjectValidation<V>.containsAnyIgnoringCase(
    values: Iterable<String>
): ObjectValidation<V> =
    this.validate({ ContainsAny(values) }) {
        it == null || values.any { e -> it.contains(other = e, ignoreCase = true) }
    }

/**
 * Validates if the [String] property doesn't contain the value
 *
 * @param value specifies the value that shouldn't contain
 */
public fun <V : String?> ObjectValidation<V>.doesNotContain(value: String): ObjectValidation<V> =
    this.validate({ NotContain(value) }) { it == null || !it.contains(value) }

/**
 * Validates if the [String] property doesn't contain the value ignoring case sensitive
 *
 * @param value specifies the value that shouldn't contain
 */
public fun <V : String?> ObjectValidation<V>.doesNotContainIgnoringCase(
    value: String
): ObjectValidation<V> =
    this.validate({ NotContain(value) }) {
        it == null || !it.contains(other = value, ignoreCase = true)
    }

/**
 * Validates if the [String] property doesn't contain all values
 *
 * @param values specifies the all values that shouldn't contain
 */
public fun <V : String?> ObjectValidation<V>.doesNotContainAll(
    vararg values: String
): ObjectValidation<V> =
    this.validate({ NotContainAll(values.toSet()) }) {
        it == null || !values.toSet().all { e -> it.contains(e) }
    }

/**
 * Validates if the [String] property doesn't contain all values
 *
 * @param values specifies the all values that shouldn't contain
 */
public fun <V : String?> ObjectValidation<V>.doesNotContainAll(
    values: Iterable<String>
): ObjectValidation<V> =
    this.validate({ NotContainAll(values) }) { it == null || !values.all { e -> it.contains(e) } }

/**
 * Validates if the [String] property doesn't contain all values ignoring case sensitive
 *
 * @param values specifies the all values that shouldn't contain
 */
public fun <V : String?> ObjectValidation<V>.doesNotContainAllIgnoringCase(
    vararg values: String
): ObjectValidation<V> =
    this.validate({ NotContainAll(values.toSet()) }) {
        it == null || !values.toSet().all { e -> it.contains(other = e, ignoreCase = true) }
    }

/**
 * Validates if the [String] property doesn't contain all values ignoring case sensitive
 *
 * @param values specifies the all values that shouldn't contain
 */
public fun <V : String?> ObjectValidation<V>.doesNotContainAllIgnoringCase(
    values: Iterable<String>
): ObjectValidation<V> =
    this.validate({ NotContainAll(values) }) {
        it == null || !values.all { e -> it.contains(other = e, ignoreCase = true) }
    }

/**
 * Validates if the [String] property doesn't contain any value
 *
 * @param values specifies the values that one shouldn't contain
 */
public fun <V : String?> ObjectValidation<V>.doesNotContainAny(
    vararg values: String
): ObjectValidation<V> =
    this.validate({ NotContainAny(values.toSet()) }) {
        it == null || !values.toSet().any { e -> it.contains(e) }
    }

/**
 * Validates if the [String] property doesn't contain any value
 *
 * @param values specifies the values that one shouldn't contain
 */
public fun <V : String?> ObjectValidation<V>.doesNotContainAny(
    values: Iterable<String>
): ObjectValidation<V> =
    this.validate({ NotContainAny(values) }) { it == null || !values.any { e -> it.contains(e) } }

/**
 * Validates if the [String] property doesn't contain any value ignoring case sensitive
 *
 * @param values specifies the values that one shouldn't contain
 */
public fun <V : String?> ObjectValidation<V>.doesNotContainAnyIgnoringCase(
    vararg values: String
): ObjectValidation<V> =
    this.validate({ NotContainAny(values.toSet()) }) {
        it == null || !values.toSet().any { e -> it.contains(other = e, ignoreCase = true) }
    }

/**
 * Validates if the [String] property doesn't contain any value ignoring case sensitive
 *
 * @param values specifies the values that one shouldn't contain
 */
public fun <V : String?> ObjectValidation<V>.doesNotContainAnyIgnoringCase(
    values: Iterable<String>
): ObjectValidation<V> =
    this.validate({ NotContainAny(values) }) {
        it == null || !values.any { e -> it.contains(other = e, ignoreCase = true) }
    }

/**
 * Validates if the [String] property matches the value
 *
 * @param regex specifies the pattern value that should match
 */
public fun <V : String?> ObjectValidation<V>.matches(regex: Regex): ObjectValidation<V> =
    this.validate({ Matches(regex) }) { it == null || it.matches(regex) }

/**
 * Validates if the [String] property doesn't match the value
 *
 * @param regex specifies the pattern value that shouldn't match
 */
public fun <V : String?> ObjectValidation<V>.doesNotMatch(regex: Regex): ObjectValidation<V> =
    this.validate({ NotMatch(regex) }) { it == null || !it.matches(regex) }

/**
 * Validates if the [String] property contains a pattern
 *
 * @param regex specifies the pattern value that should contain
 */
public fun <V : String?> ObjectValidation<V>.contains(regex: Regex): ObjectValidation<V> =
    this.validate({ ContainsRegex(regex) }) { it == null || it.contains(regex) }

/**
 * Validates if the [String] property doesn't contain the pattern
 *
 * @param regex specifies the pattern value that shouldn't contain
 */
public fun <V : String?> ObjectValidation<V>.doesNotContain(regex: Regex): ObjectValidation<V> =
    this.validate({ NotContainRegex(regex) }) { it == null || !it.contains(regex) }

/**
 * Validates if the [String] property value starts with another value
 *
 * @param prefix specifies the value that should start
 */
public fun <V : String?> ObjectValidation<V>.startsWith(prefix: String): ObjectValidation<V> =
    this.validate({ StartsWith(prefix) }) { it == null || it.startsWith(prefix) }

/**
 * Validates if the [String] property value starts with another value ignoring case sensitive
 *
 * @param prefix specifies the value that should start
 */
public fun <V : String?> ObjectValidation<V>.startsWithIgnoringCase(
    prefix: String
): ObjectValidation<V> =
    this.validate({ StartsWith(prefix) }) {
        it == null || it.startsWith(prefix = prefix, ignoreCase = true)
    }

/**
 * Validates if the [String] property value doesn't start with another value
 *
 * @param prefix specifies the value that shouldn't start
 */
public fun <V : String?> ObjectValidation<V>.doesNotStartWith(prefix: String): ObjectValidation<V> =
    this.validate({ NotStartWith(prefix) }) { it == null || !it.startsWith(prefix) }

/**
 * Validates if the [String] property value doesn't start with another value ignoring case sensitive
 *
 * @param prefix specifies the value that shouldn't start
 */
public fun <V : String?> ObjectValidation<V>.doesNotStartWithIgnoringCase(
    prefix: String
): ObjectValidation<V> =
    this.validate({ NotStartWith(prefix) }) {
        it == null || !it.startsWith(prefix = prefix, ignoreCase = true)
    }

/**
 * Validates if the [String] property value ends with another value
 *
 * @param suffix specifies the value that should end
 */
public fun <V : String?> ObjectValidation<V>.endsWith(suffix: String): ObjectValidation<V> =
    this.validate({ EndsWith(suffix) }) { it == null || it.endsWith(suffix) }

/**
 * Validates if the [String] property value ends with another value ignoring case sensitive
 *
 * @param suffix specifies the value that should end
 */
public fun <V : String?> ObjectValidation<V>.endsWithIgnoringCase(
    suffix: String
): ObjectValidation<V> =
    this.validate({ EndsWith(suffix) }) {
        it == null || it.endsWith(suffix = suffix, ignoreCase = true)
    }

/**
 * Validates if the [String] property value doesn't end with another value
 *
 * @param suffix specifies the value that shouldn't end
 */
public fun <V : String?> ObjectValidation<V>.doesNotEndWith(suffix: String): ObjectValidation<V> =
    this.validate({ NotEndWith(suffix) }) { it == null || !it.endsWith(suffix) }

/**
 * Validates if the [String] property value doesn't end with another value ignoring case sensitive
 *
 * @param suffix specifies the value that shouldn't end
 */
public fun <V : String?> ObjectValidation<V>.doesNotEndWithIgnoringCase(
    suffix: String
): ObjectValidation<V> =
    this.validate({ NotEndWith(suffix) }) {
        it == null || !it.endsWith(suffix = suffix, ignoreCase = true)
    }

/** Validates if the [String] property value is a valid email */
public fun <V : String?> ObjectValidation<V>.isEmail(): ObjectValidation<V> =
    this.validate({ Email }) {
        it == null ||
            it.matches(
                Regex(
                    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
    }

/** Validates if the [String] property value is a valid website */
public fun <V : String?> ObjectValidation<V>.isWebsite(): ObjectValidation<V> =
    this.validate({ Website }) {
        it == null ||
            it.matches(
                Regex("^(https?:\\/\\/)?([a-zA-Z0-9]+(-?[a-zA-Z0-9])*\\.)+[\\w]{2,}(\\/\\S*)?\$"))
    }
