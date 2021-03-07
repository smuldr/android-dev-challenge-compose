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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.domain.APP_FLEET
import com.example.androiddevchallenge.domain.Team
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun TeamsList(
    teams: List<Team>,
    onTeamClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        items(generateMoreTeams(teams)) { team ->
            TeamItem(
                team = team,
                modifier = Modifier
                    .clickable(onClickLabel = "Show team details") { onTeamClick(team.name) }
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.horizontal_margin),
                        vertical = dimensionResource(id = R.dimen.gutter) / 2,
                    )
            )
            Divider()
        }
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun TeamsListPreview() {
    MyTheme {
        Surface {
            TeamsList(
                teams = APP_FLEET.teams,
                onTeamClick = { },
            )
        }
    }
}

private fun generateMoreTeams(teams: List<Team>): List<Team> =
    teams.toMutableList()
        .apply {
            repeat(100) {
                addAll(teams)
            }
        }
        .toList()
