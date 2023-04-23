package com.prvz.kvalidity

import com.prvz.kvalidity.constraint.Constraint
import com.prvz.kvalidity.constraint.ConstraintViolation
import com.prvz.kvalidity.constraint.ConstraintViolationException
import com.prvz.kvalidity.constraint.DefaultConstraintViolation

class Validator<T> {

    internal val violations: MutableList<ConstraintViolation> = mutableListOf()

    fun <V> validateVal(value: V, propName: String): PipelineValue<V> =
        PipelineValue(value = value, propName = propName)

    abstract inner class Pipeline<V> {

        internal abstract val value: V?
        internal abstract val propName: String?

        protected fun addNewViolation(value: V?, constraint: Constraint) {
            this@Validator.violations +=
                DefaultConstraintViolation(
                    property = propName, value = value, constraint = constraint)
        }

        abstract fun validate(
            constraintFunc: (V) -> Constraint,
            isValid: (V) -> Boolean
        ): Pipeline<V>

        abstract fun <R> validateAndMap(
            constraintFunc: (V) -> Constraint,
            isValid: (V) -> Boolean,
            mapperFunc: (V) -> R
        ): PipelineMutated<R>

        fun validate(constraint: Constraint, isValid: (V) -> Boolean): Pipeline<V> =
            validate({ constraint }, isValid)
    }

    inner class PipelineValue<V>(override val value: V, override val propName: String) :
        Pipeline<V>() {
        override fun validate(
            constraintFunc: (V) -> Constraint,
            isValid: (V) -> Boolean
        ): PipelineValue<V> {
            if (!isValid.invoke(value)) {
                addNewViolation(value, constraintFunc.invoke(value))
            }
            return this
        }

        override fun <R> validateAndMap(
            constraintFunc: (V) -> Constraint,
            isValid: (V) -> Boolean,
            mapperFunc: (V) -> R
        ): PipelineMutated<R> =
            if (isValid.invoke(value)) {
                val mappedValue = mapperFunc.invoke(value)
                PipelineMutated(mappedValue, propName, false)
            } else {
                addNewViolation(value, constraintFunc.invoke(value))
                PipelineMutated(null, propName, true)
            }
    }

    //    inner class PipelineSelf<V>(override val value: V, override val propName: String?) :
    //        Pipeline<V>() {
    //        override fun validate(
    //            constraintFunc: (V) -> Constraint,
    //            isValid: (V) -> Boolean
    //        ): PipelineSelf<V> {
    //            if (!isValid.invoke(value)) {
    //                addNewViolation(value, constraintFunc.invoke(value))
    //            }
    //            return this
    //        }
    //    }

    /** Experimental because unsafe */
    inner class PipelineMutated<V>(
        override val value: V?,
        override val propName: String?,
        val failed: Boolean
    ) : Pipeline<V>() {

        override fun validate(
            constraintFunc: (V) -> Constraint,
            isValid: (V) -> Boolean
        ): PipelineMutated<V> {
            if (!failed) {
                if (!isValid.invoke(value as V)) {
                    addNewViolation(value, constraintFunc.invoke(value))
                }
            }
            return this
        }

        override fun <R> validateAndMap(
            constraintFunc: (V) -> Constraint,
            isValid: (V) -> Boolean,
            mapperFunc: (V) -> R
        ): PipelineMutated<R> =
            if (!failed) {
                if (!isValid.invoke(value as V)) {
                    addNewViolation(value, constraintFunc.invoke(value))
                    PipelineMutated(null, propName, true)
                } else {
                    val mappedValue = mapperFunc.invoke(value)
                    PipelineMutated(mappedValue, propName, false)
                }
            } else {
                PipelineMutated(null, propName, true)
            }
    }
}

// fun <V> validateSelf(value: V, propName: String? = null): Validator<V>.PipelineSelf<V> {
//    val validator = Validator<V>()
//    val pipeline = validator.PipelineSelf<V>(value, propName)
//    return pipeline
// }

fun <T> validate(value: T, validatorFunc: Validator<T>.(T) -> Unit): Validated<T> {
    val validator = Validator<T>()
    validatorFunc.invoke(validator, value)
    return if (validator.violations.isEmpty()) {
        Validated.Impl(value, null)
    } else Validated.Impl(value, ConstraintViolationException(validator.violations))
}
//
// fun some() {
//    validate("") {  }
// }
