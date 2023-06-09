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

package com.prvz.kvalidity.ext

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.prvz.kvalidity.Validated
import com.prvz.kvalidity.constraint.model.ConstraintViolation

public typealias ResultMonad<V, E> = com.github.michaelbull.result.Result<V, E>

public fun <T> Validated<T>.toResultMonad(): ResultMonad<T, Collection<ConstraintViolation>> =
    if (isValid()) Ok(value) else Err(constraintViolations)
