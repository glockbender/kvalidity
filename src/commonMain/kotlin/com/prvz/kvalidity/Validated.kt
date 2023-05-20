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

public sealed class Validated<V>(
    public val value: V,
    public val violation: ConstraintViolationException?
) {

    public fun isValid(): Boolean = violation == null

    public fun isNotValid(): Boolean = !isValid()

    public fun throwIfIsNotValid(): V = if (isValid()) value else throw violation!!

    public fun violations(): Collection<ConstraintViolation>? = violation?.constraintViolations

    public fun toResult(): Result<V> =
        if (isValid()) Result.success(value) else Result.failure(violation!!)

    public class Impl<V> internal constructor(value: V, violation: ConstraintViolationException?) :
        Validated<V>(value = value, violation = violation)

//    public class MappedImpl<FROM, TO>
//    internal constructor(
//        public val fromValue: FROM,
//        value: TO,
//        violation: ConstraintViolationException?
//    ) : Validated<TO>(value = value, violation = violation) {}
}
