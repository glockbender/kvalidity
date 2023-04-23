package com.prvz.kvalidity

import dev.icerock.moko.resources.StringResource
import java.util.*

actual class MokoStringResourceTranslator private actual constructor() {
    actual suspend fun localized(res: StringResource, locale: String, vararg args: Any): String =
        res.localized(Locale(locale), args)

    actual companion object Holder {
        actual val INSTANCE = MokoStringResourceTranslator()
    }
}
