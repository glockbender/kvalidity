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

package com.prvz.kvalidity.constraint

import com.prvz.kvalidity.MR
import com.prvz.kvalidity.constraint.model.Constraint
import com.prvz.kvalidity.constraint.model.ConstraintMessageProvider
import com.prvz.kvalidity.constraint.model.MokoStaticConstraintMessageProvider

/** Represents a constraint that validate if the value is [true] */
public object True : Constraint {
    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(stringResource = MR.strings.kvalidity_bundled_True)
}

/** Represents a constraint that validate if the value is [false] */
public object False : Constraint {

    override val messageProvider: ConstraintMessageProvider =
        MokoStaticConstraintMessageProvider(stringResource = MR.strings.kvalidity_bundled_False)
}
