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
package com.example.androiddevchallenge.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.domain.ATLANTIS
import com.example.androiddevchallenge.domain.Team
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.typography

@Composable
fun TeamContent(
    team: Team,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(
            horizontal = dimensionResource(id = R.dimen.horizontal_margin),
            vertical = dimensionResource(id = R.dimen.vertical_margin),
        )
    ) {
        Text(
            text = "Team name:",
            style = typography.body2,
            color = MaterialTheme.colors.onBackground.copy(alpha = 0.6f),
        )
        Text(
            text = team.name,
            style = typography.body1,
            color = MaterialTheme.colors.onBackground.copy(alpha = 0.87f),
        )
        if (team.members.isNotEmpty()) {
            val memberNames = team.members.joinToString(", ")
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Members:",
                style = typography.body2,
                color = MaterialTheme.colors.onBackground.copy(alpha = 0.6f),
            )
            Text(
                text = memberNames,
                style = typography.body1,
                color = MaterialTheme.colors.onBackground.copy(alpha = 0.87f),
            )
        }
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun TeamContentPreview() {
    MyTheme {
        Surface {
            TeamContent(team = ATLANTIS, modifier = Modifier.padding(horizontal = 16.dp))
        }
    }
}
