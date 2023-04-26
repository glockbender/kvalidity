package com.prvz.kvalidity

import dev.icerock.moko.resources.StringResource
import java.util.*

public actual class MokoStringResourceTranslator private actual constructor() {
    public actual suspend fun localized(res: StringResource, locale: String, vararg args: Any): String =
        res.localized(Locale(locale), args)

    public actual companion object Holder {
        public actual val INSTANCE:MokoStringResourceTranslator = MokoStringResourceTranslator()
    }
}
