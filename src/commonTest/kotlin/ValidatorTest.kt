import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4
import com.prvz.kvalidity.MutatedObjectValidation
import com.prvz.kvalidity.Validated
import com.prvz.kvalidity.constraint.False
import com.prvz.kvalidity.constraint.Greater
import com.prvz.kvalidity.constraint.NotNull
import com.prvz.kvalidity.constraint.Valid
import com.prvz.kvalidity.constraint.model.DefaultConstraintViolation
import com.prvz.kvalidity.functions.isEmpty
import com.prvz.kvalidity.functions.isFalse
import com.prvz.kvalidity.functions.isGreaterThan
import com.prvz.kvalidity.functions.isNotNull
import com.prvz.kvalidity.validate
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.types.shouldBeInstanceOf

class ValidatorTest : FunSpec() {

    class SomeInnerObject(val e: Int, val f: String?)
    class SomeObj(val a: Boolean, val b: String?, val c: Uuid, val d: SomeInnerObject)

    fun newSomeObject(
        a: Boolean = true,
        b: String? = null,
        c: Uuid = uuid4(),
        e: Int = 2,
        f: String = "2",
        d: SomeInnerObject = SomeInnerObject(e = e, f = f)
    ) = SomeObj(a = a, b = b, c = c, d = d)
    init {
        test("object with values validated by validateVal should have exact violations") {
            val obj = newSomeObject()
            val validated: Validated<SomeObj> =
                validate(obj) {
                    // check plain validations
                    this.validateVal(obj.a, "a").isFalse()
                    this.validateVal(obj.d.e, "d.e").isGreaterThan(3)
                    // check invalid validation with mapping
                    val nullValidation = this.validateVal(obj.b, "b").isNotNull()
                    nullValidation.shouldBeInstanceOf<MutatedObjectValidation.Invalid<String>>()
                    shouldThrow<MutatedObjectValidation.Invalid.AccessToValueException> {
                        nullValidation.value
                    }
                    shouldNotThrow<MutatedObjectValidation.Invalid.AccessToValueException> {
                        nullValidation.isEmpty()
                    }

                    shouldNotThrow<MutatedObjectValidation.Invalid.AccessToValueException> {
                        nullValidation.validateAndMap(
                            constraintBuilder = { Valid },
                            isValid = { it.isNotEmpty() },
                            mapperFunc = {})
                    }
                    // check valid validation with mapping
                    val nonNullValidation = this.validateVal(obj.d.f, "d.f").isNotNull()
                    nonNullValidation.shouldBeInstanceOf<MutatedObjectValidation.Valid<String>>()
                }
            validated.violation
                .shouldNotBeNull()
                .constraintViolations
                .shouldHaveSize(3)
                .shouldContainExactlyInAnyOrder(
                    DefaultConstraintViolation("a", obj.a, False),
                    DefaultConstraintViolation("b", obj.b, NotNull),
                    DefaultConstraintViolation("d.e", obj.d.e, Greater(3)))
        }
    }
}
