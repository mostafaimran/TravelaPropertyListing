/*
 * Copyright 2018 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.travela.propertylisting.coreandroid.util

import androidx.lifecycle.Observer

/**
 * An [Observer] for [SingleEvent]s, simplifying the pattern of checking if the [SingleEvent]'s content has
 * already been consumed.
 *
 * [onEventUnconsumedContent] is *only* called if the [SingleEvent]'s contents has not been consumed.
 */
class SingleEventObserver<T>(private val onEventUnconsumedContent: (T) -> Unit) :
    Observer<SingleEvent<T>> {

    override fun onChanged(value: SingleEvent<T>) {
        value.consume()?.run(onEventUnconsumedContent)
    }
}
