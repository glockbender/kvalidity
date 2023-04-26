package com.prvz.kvalidity.constraint

import com.prvz.kvalidity.MR

public object IsNotNull : Constraint {
    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(stringResource = MR.strings.kvalidity_bundled_True)
}
