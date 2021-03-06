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

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.domain.ATLANTIS
import com.example.androiddevchallenge.domain.Team
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.typography

@Composable
fun TeamItem(
    team: Team,
    modifier: Modifier = Modifier,
) {
    Surface(modifier = modifier) {
        Row {
            Column {
                Spacer(Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .width(40.dp)
                        .height(40.dp)
                        .background(MaterialTheme.colors.primary),
                    contentAlignment = Alignment.Center,
                    content = {
                        Image(
                            painter = painterResource(id = team.iconResource),
                            contentDescription = null,
                            modifier = Modifier
                                .width(32.dp)
                                .height(32.dp),
                            colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary),
                        )
                    }
                )
            }
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    text = team.name,
                    style = typography.h6,
                    color = MaterialTheme.colors.onBackground.copy(alpha = 0.87f),
                )
                if (team.members.isNotEmpty()) {
                    val memberNames = team.members.joinToString(", ")
                    Text(
                        text = memberNames,
                        style = typography.subtitle1,
                        color = MaterialTheme.colors.onBackground.copy(alpha = 0.6f),
                    )
                }
            }
        }
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun TeamItemPreview() {
    MyTheme {
        TeamItem(team = ATLANTIS, modifier = Modifier.padding(horizontal = 16.dp))
    }
}
