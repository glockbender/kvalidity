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

package com.prvz.kvalidity.constraint

import com.prvz.kvalidity.MR
import com.prvz.kvalidity.constraint.model.Constraint
import com.prvz.kvalidity.constraint.model.Constraint.Companion.toParamArrayOrNull
import com.prvz.kvalidity.constraint.model.ConstraintMessageProvider
import com.prvz.kvalidity.constraint.model.MokoStaticConstraintMessageProvider
import dev.icerock.moko.resources.StringResource

/** Represents a constraint that validates if the value is empty */
public object Empty : Constraint {
    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(stringResource = MR.strings.kvalidity_bundled_Empty)
}

/** Represents a constraint that validates if the value is not empty */
public object NotEmpty : Constraint {
    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(stringResource = MR.strings.kvalidity_bundled_NotEmpty)
}

/**
 * Represents a constraint that validates if the value contains another value
 *
 * @param value specifies the value that should contain
 */
public data class Contains<T>(val value: T) : Constraint {
    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(
            stringResource = MR.strings.kvalidity_bundled_Contains, value.toParamArrayOrNull())
}

/**
 * Represents a constraint that validates if the value contains all values
 *
 * @param values specifies the all values that should contain
 */
public data class ContainsAll<T>(val values: Iterable<T>) : Constraint {
    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(
            stringResource = MR.strings.kvalidity_bundled_ContainsAll, values.toParamArrayOrNull())
}

/**
 * Represents a constraint that validates if the value contains any value
 *
 * @param values specifies the values that one should contain
 */
public data class ContainsAny<T>(val values: Iterable<T>) : Constraint {
    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(
            stringResource = MR.strings.kvalidity_bundled_ContainsAny, values.toParamArrayOrNull())
}

/**
 * Represents a constraint that validates if the value doesn't contain another value
 *
 * @param value specifies the value that shouldn't contain
 */
public data class NotContain<T>(val value: T) : Constraint {
    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(
            stringResource = MR.strings.kvalidity_bundled_NotContain, value.toParamArrayOrNull())
}

/**
 * Represents a constraint that validates if the value doesn't contain all values
 *
 * @param values specifies the all values that shouldn't contain
 */
public data class NotContainAll<T>(val values: Iterable<T>) : Constraint {
    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(
            stringResource = MR.strings.kvalidity_bundled_NotContainAll,
            values.toParamArrayOrNull())
}

/**
 * Represents a constraint that validates if the value doesn't contain any value
 *
 * @param values specifies the values that one shouldn't contain
 */
public data class NotContainAny<T>(val values: Iterable<T>) : Constraint {
    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(
            stringResource = MR.strings.kvalidity_bundled_NotContainAny,
            values.toParamArrayOrNull())
}

/**
 * Represents a constraint that validates if the value size is within the limits (min and max)
 *
 * @param min specifies the minimum size
 * @param max specifies the maximum size
 */
public data class Size(val min: Int = Int.MIN_VALUE, val max: Int = Int.MAX_VALUE) : Constraint {

    private fun getData(): Pair<StringResource, Array<out Any>> =
        when {
            min != Int.MIN_VALUE && max != Int.MAX_VALUE -> {
                MR.strings.kvalidity_bundled_Size to arrayOf(this.min, this.max)
            }
            min != Int.MIN_VALUE -> {
                MR.strings.kvalidity_bundled_Size_min to arrayOf(this.min)
            }
            max != Int.MAX_VALUE -> {
                MR.strings.kvalidity_bundled_Size_max to arrayOf(this.max)
            }
            else -> {
                MR.strings.kvalidity_bundled_Size to arrayOf(this.min, this.max)
            }
        }

    override val messageProvider: ConstraintMessageProvider =
        getData().let { MokoStaticConstraintMessageProvider(it.first, it.second) }
}
