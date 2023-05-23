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

import com.prvz.kvalidity.constraint.model.Constraint
import com.prvz.kvalidity.constraint.model.DefaultConstraintViolation
import com.prvz.kvalidity.ext.UnsafeApi

/**
 * NOTE for devs extending this class:
 *
 * access to [value] with caution because i.e. [MappedObjectValidation.Invalid] is not supported
 * access to this property
 */
public sealed class ObjectValidation<V>(
    protected val validator: Validator<*>,
    protected val isStopped: Boolean
) {
    internal abstract val value: V
    internal abstract val propName: String?

    private fun addNewViolation(value: V, constraint: Constraint) {
        validator.violations +=
            DefaultConstraintViolation(property = propName, value = value, constraint = constraint)
    }

    public fun validate(
        constraintBuilder: (V) -> Constraint,
        isValid: (V) -> Boolean
    ): ObjectValidation<V> =
        if (!isStopped && !isValid(value)) {
            addNewViolation(value, constraintBuilder.invoke(value!!))
            this
        } else {
            this
        }

    public suspend fun coValidate(
        constraintBuilder: (V) -> Constraint,
        isValid: suspend (V) -> Boolean
    ): ObjectValidation<V> =
        if (!isStopped && !isValid(value)) {
            addNewViolation(value, constraintBuilder.invoke(value!!))
            this
        } else {
            this
        }

    public fun validate(constraint: Constraint, isValid: (V) -> Boolean): ObjectValidation<V> =
        validate({ constraint }, isValid)

    public suspend fun coValidate(
        constraint: Constraint,
        isValid: suspend (V) -> Boolean
    ): ObjectValidation<V> = coValidate({ constraint }, isValid)

    public open fun <R> validateAndMap(
        constraintBuilder: (V) -> Constraint,
        isValid: (V) -> Boolean,
        mapperFunc: (V) -> R
    ): MappedObjectValidation<R> =
        if (isStopped) {
            MappedObjectValidation.Invalid(validator, propName)
        } else if (isValid(value)) {
            val mappedValue = mapperFunc.invoke(value)
            MappedObjectValidation.Valid(validator, mappedValue, propName)
        } else {
            addNewViolation(value, constraintBuilder.invoke(value))
            MappedObjectValidation.Invalid(validator, propName)
        }

    public open suspend fun <R> coValidateAndMap(
        constraintBuilder: (V) -> Constraint,
        isValid: suspend (V) -> Boolean,
        mapperFunc: suspend (V) -> R
    ): MappedObjectValidation<R> =
        if (isStopped) {
            MappedObjectValidation.Invalid(validator, propName)
        } else if (isValid(value)) {
            val mappedValue = mapperFunc.invoke(value)
            MappedObjectValidation.Valid(validator, mappedValue, propName)
        } else {
            addNewViolation(value, constraintBuilder.invoke(value))
            MappedObjectValidation.Invalid(validator, propName)
        }
}

public class ValueObjectValidation<V>(
    validator: Validator<*>,
    override val value: V,
    override val propName: String?
) : ObjectValidation<V>(validator, isStopped = false)

@UnsafeApi(
    "if impl is [Invalid] access to it [value] property is not supported and throws AccessToValueException"
)
public sealed class MappedObjectValidation<V>(validator: Validator<*>, isStopped: Boolean) :
    ObjectValidation<V>(validator, isStopped) {

    public class Valid<V>(
        validator: Validator<*>,
        override val value: V,
        override val propName: String?
    ) : MappedObjectValidation<V>(validator, isStopped = false)

    @UnsafeApi("access to [value] property is not supported and throws AccessToValueException")
    public class Invalid<V>(validator: Validator<*>, override val propName: String?) :
        MappedObjectValidation<V>(validator, isStopped = true) {

        internal object AccessToValueException :
            IllegalStateException(
                "unexpected behavior: In InvalidMutatedObjectValidation value mustn't be called"
            )
        override val value: V
            get() = throw AccessToValueException

        @Suppress("UNCHECKED_CAST")
        override fun <R> validateAndMap(
            constraintBuilder: (V) -> Constraint,
            isValid: (V) -> Boolean,
            mapperFunc: (V) -> R
        ): MappedObjectValidation<R> = this as Invalid<R>

        @Suppress("UNCHECKED_CAST")
        override suspend fun <R> coValidateAndMap(
            constraintBuilder: (V) -> Constraint,
            isValid: suspend (V) -> Boolean,
            mapperFunc: suspend (V) -> R
        ): MappedObjectValidation<R> = this as Invalid<R>
    }
}
