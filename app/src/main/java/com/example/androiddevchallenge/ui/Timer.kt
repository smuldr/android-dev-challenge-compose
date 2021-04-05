/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.TimerEvent
import com.example.androiddevchallenge.TimerState
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun Timer(
    state: TimerState,
    modifier: Modifier = Modifier,
    eventListener: (TimerEvent) -> Unit = {},
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colors.secondaryVariant,
        elevation = 4.dp,
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            TimerDisplay(
                state = state,
                eventListener = eventListener,
            )
            TimerButtons(
                modifier = Modifier.padding(top = 24.dp),
                eventListener = eventListener,
            )
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun TimerLightPreview() {
    MyTheme {
        Surface {
            Timer(TimerState.Stopped(3600))
        }
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun TimerDarkPreview() {
    MyTheme(darkTheme = true) {
        Surface {
            Timer(TimerState.Stopped(754))
        }
    }
}
