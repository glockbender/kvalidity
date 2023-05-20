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

package com.prvz.kvalidity.constraint.model

public interface ConstraintViolation {
    public val property: String?
    public val value: Any?
    public val constraint: Constraint
}

public data class DefaultConstraintViolation(
    override val property: String?,
    override val value: Any? = null,
    override val constraint: Constraint
) : ConstraintViolation

/** Represents an exception that contains all the violated constraints of an object */
public class ConstraintViolationException(
    public val constraintViolations: Collection<ConstraintViolation>
) : RuntimeException()
