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

package com.prvz.kvalidity.platform

import com.prvz.kvalidity.MR
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.provider.RemoteJsStringLoader

public actual object MokoStringResourceTranslator {

    private val loader: RemoteJsStringLoader = MR.stringsLoader

    public actual suspend fun localized(
        res: StringResource,
        locale: String,
        vararg args: Any
    ): String = res.localized(loader.getOrLoad(), locale, args)
}
