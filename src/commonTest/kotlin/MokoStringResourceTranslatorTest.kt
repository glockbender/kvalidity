import com.prvz.kvalidity.MR
import com.prvz.kvalidity.MokoStringResourceTranslator
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

// From Kotest doc:
// Tests for Javascript cannot nest tests.
// This is due to the underlying Javascript test runners (such as Mocha or Karma)
// not supporting promises in parent tests,
// which is incompatible with coroutines and in Kotest every test scope is a coroutine.
// This is why the supported specs are limited to FunSpec, ShouldSpec and StringSpec.
class MokoStringResourceTranslatorTest : FunSpec() {

    init {

        val resourceTranslator = MokoStringResourceTranslator.INSTANCE

        test("ResourceTranslator.localized() should return en translation") {
            resourceTranslator.localized(MR.strings.kvalidity_bundled_Blank, "en") shouldBe
                "Must be blank"
        }

        test("ResourceTranslator.localized() should return es translation") {
            resourceTranslator.localized(MR.strings.kvalidity_bundled_Blank, "es") shouldBe
                "Tiene que estar vacÃ\u00ADo"
        }

        test("ResourceTranslator.localized() should return base translation for unknown locale") {
            resourceTranslator.localized(MR.strings.kvalidity_bundled_Blank, "unknown") shouldBe
                "Must be blank"
        }
    }
}
