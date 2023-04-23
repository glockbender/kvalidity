package com.prvz.kvalidity

import dev.icerock.moko.resources.StringResource

expect class MokoStringResourceTranslator private constructor() {
    suspend fun localized(res: StringResource, locale: String, vararg args: Any): String

    companion object Holder {
        val INSTANCE: MokoStringResourceTranslator
    }
}
