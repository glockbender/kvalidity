package com.prvz.kvalidity

import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.provider.RemoteJsStringLoader

public actual class MokoStringResourceTranslator private actual constructor() {

    private val loader: RemoteJsStringLoader = MR.stringsLoader

    public actual suspend fun localized(res: StringResource, locale: String, vararg args: Any): String =
        res.localized(loader.getOrLoad(), locale, args)

    public actual companion object Holder {
        public actual val INSTANCE: MokoStringResourceTranslator = MokoStringResourceTranslator()
    }
}
