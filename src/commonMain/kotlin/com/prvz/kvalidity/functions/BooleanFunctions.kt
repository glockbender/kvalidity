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
import com.prvz.kvalidity.constraint.Constraint
import com.prvz.kvalidity.constraint.False
import com.prvz.kvalidity.constraint.True

/** Validates if the [Boolean] property is true */
public fun <T, V : Boolean?> Validator<T>.Pipeline<V>.isTrue(
    constraintFunc: (V?) -> Constraint = { True }
): Validator<T>.Pipeline<V> = this.validate(constraintFunc) { it == null || it }

/** Validates if the [Boolean] property is false */
public fun <T, V : Boolean?> Validator<T>.Pipeline<V>.isFalse(
    constraintFunc: (V?) -> Constraint = { False }
): Validator<T>.Pipeline<V> = this.validate(constraintFunc) { it == null || !it }
