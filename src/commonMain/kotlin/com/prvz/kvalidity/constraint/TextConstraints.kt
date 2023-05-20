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
import com.prvz.kvalidity.constraint.model.ConstraintMessageProvider
import com.prvz.kvalidity.constraint.model.MokoStaticConstraintMessageProvider

/** Represents a constraint that validates if the value is blank */
public object Blank : Constraint {

    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(stringResource = MR.strings.kvalidity_bundled_Blank)
}

/** Represents a constraint that validates if the value is not blank */
public object NotBlank : Constraint {

    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(stringResource = MR.strings.kvalidity_bundled_NotBlank)
}

/** Represents a constraint that validates if the value is letter */
public object Letter : Constraint {

    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(stringResource = MR.strings.kvalidity_bundled_Letter)
}

/** Represents a constraint that validates if the value is not letter */
public object NotLetter : Constraint {

    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(stringResource = MR.strings.kvalidity_bundled_NotLetter)
}

/** Represents a constraint that validates if the value is digit */
public object Digit : Constraint {

    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(stringResource = MR.strings.kvalidity_bundled_Digit)
}

/** Represents a constraint that validates if the value is not digit */
public object NotDigit : Constraint {

    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(stringResource = MR.strings.kvalidity_bundled_NotDigit)
}

/** Represents a constraint that validates if the value is letter or digit */
public object LetterOrDigit : Constraint {

    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(
            stringResource = MR.strings.kvalidity_bundled_LetterOrDigit
        )
}

/** Represents a constraint that validates if the value is not letter or digit */
public object NotLetterOrDigit : Constraint {

    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(
            stringResource = MR.strings.kvalidity_bundled_NotLetterOrDigit
        )
}

/** Represents a constraint that validates if the value is uppercase */
public object UpperCase : Constraint {

    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(stringResource = MR.strings.kvalidity_bundled_UpperCase)
}

/** Represents a constraint that validates if the value is lowercase */
public object LowerCase : Constraint {

    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(stringResource = MR.strings.kvalidity_bundled_LowerCase)
}

/**
 * Represents a constraint that validates if the value matches with a pattern
 *
 * @param pattern specifies the pattern value that should match
 */
public data class Matches(val pattern: String) : Constraint {

    public constructor(regex: Regex) : this(regex.pattern)

    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider.of(
            stringResource = MR.strings.kvalidity_bundled_Matches,
            pattern
        )
}

/**
 * Represents a constraint that validates if the value doesn't match with a pattern
 *
 * @param pattern specifies the pattern value that shouldn't match
 */
public data class NotMatch(val pattern: String) : Constraint {
    public constructor(regex: Regex) : this(regex.pattern)

    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider.of(
            stringResource = MR.strings.kvalidity_bundled_NotMatch,
            pattern
        )
}

/**
 * Represents a constraint that validates if the value contains a pattern
 *
 * @param pattern specifies the pattern value that should contain
 */
public data class ContainsRegex(val pattern: String) : Constraint {
    public constructor(regex: Regex) : this(regex.pattern)

    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider.of(
            stringResource = MR.strings.kvalidity_bundled_ContainsRegex,
            pattern
        )
}

/**
 * Represents a constraint that validates if the value doesn't contain a pattern
 *
 * @param pattern specifies the value pattern that shouldn't contain
 */
public data class NotContainRegex(val pattern: String) : Constraint {
    public constructor(regex: Regex) : this(regex.pattern)

    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider.of(
            stringResource = MR.strings.kvalidity_bundled_NotContainRegex,
            pattern
        )
}

/**
 * Represents a constraint that validates if the value starts with another value
 *
 * @param prefix specifies the value that should start
 */
public data class StartsWith(val prefix: String) : Constraint {

    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider.of(
            stringResource = MR.strings.kvalidity_bundled_StartsWith,
            prefix
        )
}

/**
 * Represents a constraint that validates if the value doesn't start with another value
 *
 * @param prefix specifies the value that shouldn't start
 */
public data class NotStartWith(val prefix: String) : Constraint {

    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider.of(
            stringResource = MR.strings.kvalidity_bundled_NotStartWith,
            prefix
        )
}

/**
 * Represents a constraint that validates if the value ends with another value
 *
 * @param suffix specifies the value that should end
 */
public data class EndsWith(val suffix: String) : Constraint {

    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider.of(
            stringResource = MR.strings.kvalidity_bundled_EndsWith,
            suffix
        )
}

/**
 * Represents a constraint that validates if the value doesn't end with another value
 *
 * @param suffix specifies the value that not shouldn't end
 */
public data class NotEndWith(val suffix: String) : Constraint {

    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider.of(
            stringResource = MR.strings.kvalidity_bundled_NotEndWith,
            suffix
        )
}

/** Represents a constraint that validates if the value is a valid e-mail */
public object Email : Constraint {
    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(stringResource = MR.strings.kvalidity_bundled_Email)
}

/** Represents a constraint that validates if the value is a valid website */
public object Website : Constraint {
    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(stringResource = MR.strings.kvalidity_bundled_Website)
}
