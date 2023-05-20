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

public class Validator<T> {

    internal val violations: MutableList<ConstraintViolation> = mutableListOf()

    public fun <V> validateVal(value: V, propName: String): ValueObjectValidation<V> =
        ValueObjectValidation(this, value = value, propName = propName)
}

public fun <V> Validator<V>.toValidated(value: V): Validated<V> =
    if (this.violations.isEmpty()) {
        Validated.Impl(value, emptyList())
    } else Validated.Impl(value, this.violations)

public inline fun <V> V.validateSelf(
    propName: String? = null,
    validation: ObjectValidation<V>.(V) -> Unit
): Validated<V> {
    val validator = Validator<V>()
    val objValidation = ValueObjectValidation(validator, value = this, propName = propName)
    validation.invoke(objValidation, this)
    return validator.toValidated(this)
}

public inline fun <V> validate(value: V, validatorFunc: Validator<V>.(V) -> Unit): Validated<V> {
    val validator = Validator<V>()
    validatorFunc.invoke(validator, value)
    return validator.toValidated(value)
}
