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

package com.prvz.kvalidity

import com.prvz.kvalidity.constraint.model.ConstraintViolation
import com.prvz.kvalidity.constraint.model.ConstraintViolationException
import kotlinx.coroutines.coroutineScope

public sealed class Validated<V>(
    public val value: V,
    public val constraintViolations: Collection<ConstraintViolation>
) {

    public fun isValid(): Boolean = constraintViolations.isEmpty()

    public fun isNotValid(): Boolean = !isValid()

    public suspend fun throwIfIsNotValid(): V = coroutineScope {
        if (isValid()) value else throw generateException()
    }

    public suspend fun toResult(): Result<V> =
        if (isValid()) Result.success(value) else Result.failure(generateException())

    public class Impl<V>
    internal constructor(
        value: V,
        constraintViolations: Collection<ConstraintViolation> = emptyList()
    ) : Validated<V>(value = value, constraintViolations = constraintViolations)

    private suspend fun generateException(): ConstraintViolationException {
        val locale = LocaleDispatcher.getLocale()
        val violationMessages =
            constraintViolations.map {
                val propName = it.property ?: "unknown"
                val message = it.constraint.messageProvider.getMessage(locale)
                "$propName: ${message ?: "Constraint violation message unavailable. Obj: $it"}"
            }
        return ConstraintViolationException(violationMessages)
    }
}
