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
package com.example.androiddevchallenge.domain

import androidx.annotation.DrawableRes
import com.example.androiddevchallenge.R

data class Team(
    val name: String,
    @DrawableRes val iconResource: Int,
    val members: List<String>,
)

val ATLANTIS = Team(
    name = "Atlantis",
    iconResource = R.drawable.ic_spaceship,
    members = listOf("Tim", "Jos", "Pitu", "Marvin", "Rick", "Marsoor", "Steven", "Carla")
)

val DRAGON = Team(
    name = "Dragon",
    iconResource = R.drawable.ic_rocket,
    members = listOf("Melle", "Linda", "Daniel", "Rachid", "Yehia", "Abelrahman", "Bram", "Kim")
)

val PATHFINDER = Team(
    name = "Pathfinder",
    iconResource = R.drawable.ic_rover,
    members = listOf("Mathijs", "Dionysios", "Leanne", "Oscar", "Paul")
)

val STARSHIP = Team(
    name = "Starship",
    iconResource = R.drawable.ic_space_capsule,
    members = listOf("Michiel", "Nikolas", "Ruben", "Rowan", "Kilian")
)

val ORION = Team(
    name = "Orion",
    iconResource = R.drawable.ic_orion,
    members = listOf("Daan", "Antria", "Jolanda", "Melih")
)
