/*
 * Copyright 2018-2020 https://www.valiktor.org
 *
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

package com.prvz.kvalidity.constraint

import com.prvz.kvalidity.MR
import com.prvz.kvalidity.constraint.model.Constraint
import com.prvz.kvalidity.constraint.model.Constraint.Companion.toParamArrayOrNull
import com.prvz.kvalidity.constraint.model.ConstraintMessageProvider
import com.prvz.kvalidity.constraint.model.MokoStaticConstraintMessageProvider

/** Represents a constraint that validates if the value is null */
public object Null : Constraint {
    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(stringResource = MR.strings.kvalidity_bundled_Null)
}

/** Represents a constraint that validates if the value is not null */
public object NotNull : Constraint {
    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(stringResource = MR.strings.kvalidity_bundled_NotNull)
}

/**
 * Represents a constraint that validates if the value is equal to another value
 *
 * @param value specifies the value that should be equal
 */
public data class Equals<T>(val value: T) : Constraint {
    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(
            stringResource = MR.strings.kvalidity_bundled_Equals,
            params = value?.toString().toParamArrayOrNull()
        )
}

/**
 * Represents a constraint that validates if the value isn't equal to another value
 *
 * @param value specifies the value that should not be equal
 */
public data class NotEquals<T>(val value: T) : Constraint {
    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(
            stringResource = MR.strings.kvalidity_bundled_NotEquals,
            params = value?.toString().toParamArrayOrNull()
        )
}

/**
 * Represents a constraint that validates if the value is equal to one of the values
 *
 * @param values specifies the values to be compared
 */
public data class In<T>(val values: Iterable<T>) : Constraint {
    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(
            stringResource = MR.strings.kvalidity_bundled_Equals,
            params = values.toParamArrayOrNull()
        )
}

/**
 * Represents a constraint that validates if the value isn't equal to any value
 *
 * @param values specifies the values to be compared
 */
public data class NotIn<T>(val values: Iterable<T>) : Constraint {
    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(
            stringResource = MR.strings.kvalidity_bundled_NotIn,
            params = values.toParamArrayOrNull()
        )
}

/**
 * Represents a constraint that validates if the value is valid by passing a custom function
 *
 * @param validator specifies the validation function
 */
public object Valid : Constraint {
    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(stringResource = MR.strings.kvalidity_bundled_Valid)
}
