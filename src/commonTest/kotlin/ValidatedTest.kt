import com.prvz.kvalidity.LocaleDispatcher
import com.prvz.kvalidity.constraint.model.ConstraintViolationException
import com.prvz.kvalidity.functions.isEmpty
import com.prvz.kvalidity.functions.isEqualTo
import com.prvz.kvalidity.validateSelf
import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.FunSpec

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

class ValidatedTest : FunSpec() {

    init {

        LocaleDispatcher.setCustomLocale("en")

        test("throwIfIsNotValid() should generate exception with expected message") {
            val validated = "123".validateSelf("test") { isEmpty().isEqualTo("321") }
            shouldThrowWithMessage<ConstraintViolationException>(
                "test: Must be empty; test: Must be equal to [321]") {
                    validated.throwIfIsNotValid()
                }
        }
    }
}
