package com.prvz.kvalidity

import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.ResourceFormatted
import dev.icerock.moko.resources.desc.StringDesc

public actual class MokoStringResourceTranslator private actual constructor() {
    public actual suspend fun localized(res: StringResource, locale: String, vararg args: Any): String {

        StringDesc.localeType = StringDesc.LocaleType.Custom(locale)

        return StringDesc.ResourceFormatted(res, args).localized()
    }

    public actual companion object Holder {
        public actual val INSTANCE:MokoStringResourceTranslator = MokoStringResourceTranslator()
    }
}
