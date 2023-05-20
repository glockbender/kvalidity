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
import dev.icerock.moko.resources.StringResource

/**
 * Represents a constraint that validates if the integer value (before decimal separator) is within
 * the limits (min and max)
 */
public data class IntegerDigits(val min: Int = Int.MIN_VALUE, val max: Int = Int.MAX_VALUE) :
    Constraint {

    private fun getData(): Pair<StringResource, Array<out Any>> =
        when {
            min != Int.MIN_VALUE && max != Int.MAX_VALUE -> {
                MR.strings.kvalidity_bundled_IntegerDigits to arrayOf(this.min, this.max)
            }
            min != Int.MIN_VALUE -> {
                MR.strings.kvalidity_bundled_IntegerDigits_min to arrayOf(this.min)
            }
            max != Int.MAX_VALUE -> {
                MR.strings.kvalidity_bundled_IntegerDigits_max to arrayOf(this.max)
            }
            else -> {
                MR.strings.kvalidity_bundled_IntegerDigits to arrayOf(this.min, this.max)
            }
        }

    override val messageProvider: ConstraintMessageProvider =
        getData().let { MokoStaticConstraintMessageProvider(it.first, it.second) }
}

/**
 * Represents a constraint that validates if the decimal value (after decimal separator) is within
 * the limits (min and max)
 */
public data class DecimalDigits(val min: Int = Int.MIN_VALUE, val max: Int = Int.MAX_VALUE) :
    Constraint {

    private fun getData(): Pair<StringResource, Array<out Any>> =
        when {
            min != Int.MIN_VALUE && max != Int.MAX_VALUE -> {
                MR.strings.kvalidity_bundled_DecimalDigits to arrayOf(this.min, this.max)
            }
            min != Int.MIN_VALUE -> {
                MR.strings.kvalidity_bundled_DecimalDigits_min to arrayOf(this.min)
            }
            max != Int.MAX_VALUE -> {
                MR.strings.kvalidity_bundled_DecimalDigits_max to arrayOf(this.max)
            }
            else -> {
                MR.strings.kvalidity_bundled_DecimalDigits to arrayOf(this.min, this.max)
            }
        }

    override val messageProvider: ConstraintMessageProvider =
        getData().let { MokoStaticConstraintMessageProvider(it.first, it.second) }
}
