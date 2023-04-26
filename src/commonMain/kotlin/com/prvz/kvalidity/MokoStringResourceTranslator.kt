package com.prvz.kvalidity

import dev.icerock.moko.resources.StringResource

public expect class MokoStringResourceTranslator private constructor() {
    public suspend fun localized(res: StringResource, locale: String, vararg args: Any): String

    public companion object Holder {
        public val INSTANCE: MokoStringResourceTranslator
    }
}
