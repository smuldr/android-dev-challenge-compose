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
package com.example.androiddevchallenge.ui.overview

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.domain.APP_FLEET
import com.example.androiddevchallenge.domain.Fleet
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun FleetOverview(fleet: Fleet, modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Hello, ${fleet.name}!") },
                backgroundColor = MaterialTheme.colors.surface,
            )
        }
    ) { innerPadding ->
        TeamsList(teams = fleet.teams, modifier = Modifier.padding(innerPadding))
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun FleetOverviewPreview() {
    MyTheme {
        FleetOverview(APP_FLEET)
    }
}
