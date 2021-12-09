/*
 * Copyright (c) 2021 SQLines
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sqlines.studio.model.tabsdata.listener;

import org.jetbrains.annotations.NotNull;

/**
 * A ModeChangeListener is notified whenever the conversion mode in any tab changes.
 */
@FunctionalInterface
public interface ModeChangeListener {

    /**
     * Called when the conversion mode changes.
     *
     * @param newMode updated mode
     * @param tabIndex the index of the tab where the mode was changed
     */
    void changed(@NotNull String newMode, int tabIndex);
}
