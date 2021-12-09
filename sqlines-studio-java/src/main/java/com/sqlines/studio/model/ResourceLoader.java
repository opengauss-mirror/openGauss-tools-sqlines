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

package com.sqlines.studio.model;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Consumer;

/**
 * Works with application resources.
 */
public class ResourceLoader {

    /**
     * Loads source conversion mode from the application resources.
     *
     * @return list of source modes
     *
     * @throws IllegalStateException if the source modes file was not found
     * in application resources or contains invalid data
     * @throws IOException if any IO error occurred
     */
    public static @NotNull List<String> loadSourceModes() throws IOException {
        List<String> sourceModes = new ArrayList<>();
        try (InputStream stream = ResourceLoader.class.getResourceAsStream("/source-modes.txt")) {
            if (stream == null) {
                String errorMsg = "File not found in application resources: source-modes.txt";
                throw new IllegalStateException(errorMsg);
            }

            String data = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
            StringTokenizer tokenizer = new StringTokenizer(data, "\n");
            while (tokenizer.hasMoreTokens()) {
                String word = tokenizer.nextToken();
                int endIndex = word.indexOf(':');
                sourceModes.add(word.substring(0, endIndex));
            }
        }

        return sourceModes;
    }

    /**
     * Loads target conversion modes from the application resources.
     *
     * @return list of target modes
     *
     * @throws IllegalStateException if the target modes file was not found
     * in application resources or contains invalid data
     * @throws IOException if any IO error occurred
     */
    public static @NotNull List<String> loadTargetModes() throws IOException {
        List<String> targetModes = new ArrayList<>();
        try (InputStream stream = ResourceLoader.class.getResourceAsStream("/target-modes.txt")) {
            if (stream == null) {
                String errorMsg = "File not found in application resources: target-modes.txt";
                throw new IllegalStateException(errorMsg);
            }

            String data = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
            StringTokenizer tokenizer = new StringTokenizer(data, "\n");
            while (tokenizer.hasMoreTokens()) {
                String word = tokenizer.nextToken();
                int endIndex = word.indexOf(':');
                targetModes.add(word.substring(0, endIndex));
            }
        }

        return targetModes;
    }

    /**
     * Loads a map of conversion modes with their command-line
     * designations from the application resources.
     *
     * @return a map of conversion modes with their command-line designations.
     *
     * @throws IllegalStateException if either source modes file or target modes file was not found
     * in application resources or contains invalid data
     * @throws IOException if any IO error occurred
     */
    public static @NotNull Map<String, String> loadCmdModes() throws IOException {
        Map<String, String> cmdModes = new HashMap<>();
        try (InputStream sourceModes = ResourceLoader.class.getResourceAsStream("/source-modes.txt");
             InputStream targetModes = ResourceLoader.class.getResourceAsStream("/target-modes.txt")) {
            if (sourceModes == null || targetModes == null) {
                String errorMsg = "File not found in application resources:" +
                        "source-modes.txt or target-modes.txt";
                throw new IllegalStateException(errorMsg);
            }

            String sourceData = new String(sourceModes.readAllBytes(), StandardCharsets.UTF_8);
            String targetData = new String(targetModes.readAllBytes(), StandardCharsets.UTF_8);
            StringTokenizer sourceTokenizer = new StringTokenizer(sourceData, "\n");
            StringTokenizer targetTokenizer = new StringTokenizer(targetData, "\n");

            Consumer<String> addItem = data -> {
                int endIndex = data.indexOf(':');
                String key = data.substring(0, endIndex);
                String value = data.substring(endIndex + 1);
                cmdModes.put(key, value);
            };

            while (sourceTokenizer.hasMoreTokens()) {
                addItem.accept(sourceTokenizer.nextToken());
            }

            while (targetTokenizer.hasMoreTokens()) {
                addItem.accept(targetTokenizer.nextToken());
            }
        }

        return cmdModes;
    }

    /**
     * Loads main window light stylesheets from the application resources.
     *
     * @return main window light stylesheets
     *
     * @throws IllegalStateException if main window light stylesheets were not found
     * in application resources
     */
    public static @NotNull String loadMainLightStyles() {
        URL mainLight = ResourceLoader.class.getResource("/styles/main-light.css");
        if (mainLight == null) {
           String errorMsg = "File not found in application resources: styles/main-light.css";
           throw new IllegalStateException(errorMsg);
        }

        return mainLight.toExternalForm();
    }

    /**
     * Loads main window dark stylesheets from the application resources.
     *
     * @return main window dark stylesheets
     *
     * @throws IllegalStateException if main window dark stylesheets were not found
     * in application resources
     */
    public static @NotNull String loadMainDarkStyles() {
        URL mainDark = ResourceLoader.class.getResource("/styles/main-dark.css");
        if (mainDark == null) {
            String errorMsg = "File not found in application resources: styles/main-dark.css";
            throw new IllegalStateException(errorMsg);
        }

        return mainDark.toExternalForm();
    }


    /**
     * Loads settings window light stylesheets from the application resources.
     *
     * @return settings window light stylesheets
     *
     * @throws IllegalStateException if settings window light stylesheets were not found
     * in application resources
     */
    public static @NotNull String loadSettingLightStyles() {
        URL settingsLight = ResourceLoader.class.getResource("/styles/settings-light.css");
        if (settingsLight == null) {
            String errorMsg = "File not found in application resources: styles/settings-light.css";
            throw new IllegalStateException(errorMsg);
        }

        return settingsLight.toExternalForm();
    }

    /**
     * Loads settings window dark stylesheets from the application resources.
     *
     * @return settings window dark stylesheets
     *
     * @throws IllegalStateException if settings window dark stylesheets were not found
     * in application resources
     */
    public static @NotNull String loadSettingDarkStyles() {
        URL settingsDark = ResourceLoader.class.getResource("/styles/settings-dark.css");
        if (settingsDark == null) {
            String errorMsg = "File not found in application resources: styles/settings-dark.css";
            throw new IllegalStateException(errorMsg);
        }

        return settingsDark.toExternalForm();
    }
}
