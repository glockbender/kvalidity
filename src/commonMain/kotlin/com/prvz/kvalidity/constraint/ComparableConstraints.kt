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
import com.prvz.kvalidity.constraint.Constraint.Companion.toParamArrayOrNull

/** Represents a constraint that validates if the value is less than another value */
data class Less<T>(val value: T) : Constraint {
    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(
            stringResource = MR.strings.kvalidity_bundled_Less, params = value.toParamArrayOrNull())
}

/** Represents a constraint that validates if the value is less than or equal to another value */
data class LessOrEqual<T>(val value: T) : Constraint {
    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(
            stringResource = MR.strings.kvalidity_bundled_LessOrEqual,
            params = value.toParamArrayOrNull())
}

/** Represents a constraint that validate if the value is greater than another value */
data class Greater<T>(val value: T) : Constraint {
    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(
            stringResource = MR.strings.kvalidity_bundled_Greater,
            params = value.toParamArrayOrNull())
}

/** Represents a constraint that validate if the value is greater than or equal to another value */
data class GreaterOrEqual<T>(val value: T) : Constraint {
    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(
            stringResource = MR.strings.kvalidity_bundled_GreaterOrEqual,
            params = value.toParamArrayOrNull())
}

/** Represents a constraint that validates if the value is between two values */
data class Between<T>(val start: T, val end: T) : Constraint {
    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(
            stringResource = MR.strings.kvalidity_bundled_Between,
            params = (start to end).toParamArrayOrNull())
}

/** Represents a constraint that validates if the value isn't between two values */
data class NotBetween<T>(val start: T, val end: T) : Constraint {
    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(
            stringResource = MR.strings.kvalidity_bundled_NotBetween,
            params = (start to end).toParamArrayOrNull())
}
