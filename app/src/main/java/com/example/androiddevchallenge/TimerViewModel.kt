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
package com.example.androiddevchallenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.math.min

class TimerViewModel : ViewModel() {

    private val _state =
        MutableStateFlow<TimerState>(TimerState.Stopped(timerSeconds = INITIAL_COUNT))
    val state: StateFlow<TimerState> = _state

    private var isRunning = AtomicBoolean(false)
    private var timerJob: Job? = null

    fun onTimerEvent(event: TimerEvent) {
        when (event) {
            TimerEvent.SECOND_BUTTON_CLICK -> addTime(ONE_SECOND)
            TimerEvent.MINUTE_BUTTON_CLICK -> addTime(ONE_MINUTE)
            TimerEvent.START_STOP_BUTTON_CLICK -> onStartStopButtonClicked()
            TimerEvent.RESET_CLICK -> resetTimer()
        }
    }

    private fun addTime(secondsToAdd: Int) {
        viewModelScope.launch {
            val newTimerSeconds = min(MAX_COUNT, state.value.timerSeconds + secondsToAdd)
            val newState = when (val currentState = _state.value) {
                is TimerState.Stopped ->
                    currentState.copy(timerSeconds = newTimerSeconds)
                is TimerState.Running ->
                    currentState.copy(timerSeconds = newTimerSeconds)
            }
            _state.emit(newState)
        }
    }

    private fun onStartStopButtonClicked() {
        if (isRunning.get()) {
            stopTimer()
        } else {
            startTimer()
        }
    }

    private fun startTimer() {
        timerJob?.cancel()
        isRunning.set(true)
        timerJob = viewModelScope.launch {
            val totalSeconds = state.value.timerSeconds
            _state.emit(TimerState.Running(timerSeconds = totalSeconds, isDividerBlinking = true))
            while (state.value is TimerState.Running && state.value.timerSeconds > 0) {
                val initialState = state.value as? TimerState.Running ?: return@launch
                _state.emit(initialState.copy(isDividerBlinking = true))

                delay(HALF_SECOND_IN_MILLIS)
                val intermediateState = state.value as? TimerState.Running ?: return@launch
                _state.emit(intermediateState.copy(isDividerBlinking = false))

                delay(HALF_SECOND_IN_MILLIS)
                val finalState = state.value as? TimerState.Running ?: return@launch
                _state.emit(finalState.copy(timerSeconds = finalState.timerSeconds - ONE_SECOND))
            }
            _state.emit(TimerState.Stopped(timerSeconds = INITIAL_COUNT))
            isRunning.set(false)
        }
    }

    private fun stopTimer() {
        timerJob?.cancel()
        timerJob = null
        isRunning.set(false)
        viewModelScope.launch {
            _state.emit(TimerState.Stopped(timerSeconds = state.value.timerSeconds))
        }
    }

    private fun resetTimer() {
        stopTimer()
        viewModelScope.launch {
            _state.emit(TimerState.Stopped(timerSeconds = INITIAL_COUNT))
        }
    }

    companion object {
        private const val INITIAL_COUNT = 0
        private const val MAX_COUNT = 99 * 60 + 59 // 99:59
        private const val ONE_MINUTE = 60
        private const val ONE_SECOND = 1
        private const val HALF_SECOND_IN_MILLIS = 500L
    }
}
