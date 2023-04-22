package com.prvz.kvalidity

class Validation {

}


/**
 * val someObject = SomeObj(
 *    a = 1,
 *    b = "2",
 *    c = UUID.randomUUID(),
 *    d = SomeOtherObj(i = 1, k = "2")
 * )
 *
 * // how to validate?
 *
 * val validated: Validated<SomeObj> = validate<SomeObj>(someObject) {
 *      // validation by values
 *      validateVal(value = this.a, propName = "a").positive().lessThan(2)
 *      validateVal(value = this.b, propName = "b").isNotBlank().hasSize(3)
 *      validateVal(value = this.c, propName = "c").isUUID()
 *      // validation by property
 *      validateProp(SomeObj::a).isPositive()
 *      //
 *      validateFunc(func = SomeObj::getA, propName = {KCallable.getSimpleName.}).isPositive()
 *      ....
 *      // nested object validation
 *      validateObj<SomeOtherObj>(this.d) {
 *          validVal(this.i).isPositive()
 *          validVal(this.k).isNotEmpty().contains("2")
 *      }
 *
 *      validateIterable<SomeOtherObj>(this)
 * }
 *
 * // how to handle validation?
 *
 * // throw ConstraintViolationException
 * validated.throwIfInvalid()
 *
 * // to kotlin.Result<SomeObj> or ConstraintViolationException as failure
 * validated.toResult()
 *
 * // to com.github.michaelbull.result.Result<SomeObj, ConstraintViolationException>
 * validated.toEitherResult()
 *
 */