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

package com.prvz.kvalidity.constraint.model

import com.prvz.kvalidity.MokoStringResourceTranslator
import dev.icerock.moko.resources.StringResource

public interface ConstraintMessageProvider {
    public suspend fun getMessage(locale: String): String?
}

public class MokoStaticConstraintMessageProvider(
    private val stringResource: StringResource,
    private val params: Array<out Any>? = null,
    private val stringResourceTranslator: MokoStringResourceTranslator =
        MokoStringResourceTranslator.INSTANCE
) : ConstraintMessageProvider {

    public companion object {
        public fun of(
            stringResource: StringResource,
            vararg params: Any
        ): MokoStaticConstraintMessageProvider =
            MokoStaticConstraintMessageProvider(stringResource = stringResource, params = params)
    }

    override suspend fun getMessage(locale: String): String? =
        runCatching {
                if (params.isNullOrEmpty()) {
                    stringResourceTranslator.localized(res = stringResource, locale = locale)
                } else {
                    stringResourceTranslator.localized(
                        res = stringResource, locale = locale, *params)
                }
            }
            .getOrNull()
}
