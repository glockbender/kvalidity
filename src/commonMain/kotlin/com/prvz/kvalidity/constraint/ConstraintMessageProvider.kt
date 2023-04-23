package com.prvz.kvalidity.constraint

import com.prvz.kvalidity.MokoStringResourceTranslator
import dev.icerock.moko.resources.StringResource

interface ConstraintMessageProvider {
    suspend fun getMessage(locale: String): String?
}

class MokoStaticConstraintMessageProvider(
    private val stringResource: StringResource,
    private val params: Array<out Any>? = null,
    private val stringResourceTranslator: MokoStringResourceTranslator =
        MokoStringResourceTranslator.INSTANCE
) : ConstraintMessageProvider {

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
