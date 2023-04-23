package com.prvz.kvalidity

import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.provider.RemoteJsStringLoader

actual class MokoStringResourceTranslator private actual constructor() {

    private val loader: RemoteJsStringLoader = MR.stringsLoader

    actual suspend fun localized(res: StringResource, locale: String, vararg args: Any): String =
        res.localized(loader.getOrLoad(), locale, args)

    actual companion object Holder {
        actual val INSTANCE = MokoStringResourceTranslator()
    }
}
