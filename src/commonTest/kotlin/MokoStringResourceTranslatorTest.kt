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

import com.prvz.kvalidity.MR
import com.prvz.kvalidity.platform.MokoStringResourceTranslator
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

        val resourceTranslator = MokoStringResourceTranslator

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
