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

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.TimerEvent
import com.example.androiddevchallenge.TimerState
import com.example.androiddevchallenge.ui.theme.MyTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TimerDisplay(
    state: TimerState,
    modifier: Modifier = Modifier,
    eventListener: (TimerEvent) -> Unit = {},
) {
    val remainingTime = state.timerSeconds
    val secondsComponent = remainingTime % 60
    val minutesComponent = (remainingTime - secondsComponent) / 60
    Surface(
        modifier = modifier
            .combinedClickable(
                onLongClick = { eventListener(TimerEvent.RESET_CLICK) },
                onClick = {},
            )
            .fillMaxWidth(),
        color = MaterialTheme.colors.surface,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colors.onSecondary.copy(alpha = 0.32f)
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = createTimeString(minutesComponent, secondsComponent),
            modifier = Modifier.padding(vertical = 16.dp).fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Monospace,
            style = MaterialTheme.typography.h4
        )

        // Draw the divider separately for a smooth fading animation.
        val isDividerBlinking = state is TimerState.Running && state.isDividerBlinking
        Crossfade(targetState = isDividerBlinking,) { blink ->
            Text(
                text = if (blink) " " else ":",
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Monospace,
                style = MaterialTheme.typography.h4
            )
        }
    }
}

private fun createTimeString(minutes: Int, seconds: Int): String {
    val minsString = minutes.toString().padStart(2, '0')
    val secsString = seconds.toString().padStart(2, '0')
    return "$minsString $secsString"
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun TimerDisplayLightPreview() {
    MyTheme {
        Surface {
            TimerDisplay(TimerState.Stopped(3600))
        }
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun TimerDisplayDarkPreview() {
    MyTheme(darkTheme = true) {
        Surface {
            TimerDisplay(TimerState.Stopped(754))
        }
    }
}
