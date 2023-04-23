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

/**
 * val someObject = SomeObj( a = 1, b = "2", c = UUID.randomUUID(), d = SomeOtherObj(i = 1, k = "2")
 * )
 *
 * // how to validate?
 *
 * // validate object itself val validated: Validated<String> =
 * validateSelf("abc").isNotBlank().hasSize(3)
 *
 * // validate object inner state val validated: Validated<SomeObj> = validate<SomeObj>(someObject)
 * { // validation by values validateVal(value = this.a, propName = "a").positive().lessThan(2)
 * validateVal(value = this.b, propName = "b").isNotBlank().hasSize(3) validateVal(value = this.c,
 * propName = "c").isUUID() // validation by property validateProp(SomeObj::a).isPositive() //
 * validateFunc(func = SomeObj::getA, propName = {KCallable.getSimpleName.}).isPositive() .... //
 * nested object validation validateObj<SomeOtherObj>(this.d) { validVal(this.i).isPositive()
 * validVal(this.k).isNotEmpty().contains("2") }
 *
 *      validateIterable<SomeOtherObj>(this)
 *
 * }
 *
 * // how to handle validation?
 *
 * // throw ConstraintViolationException validated.throwIfInvalid()
 *
 * // to kotlin.Result<SomeObj> or ConstraintViolationException as failure validated.toResult()
 *
 * // to com.github.michaelbull.result.Result<SomeObj, ConstraintViolationException>
 * validated.toEitherResult()
 */
