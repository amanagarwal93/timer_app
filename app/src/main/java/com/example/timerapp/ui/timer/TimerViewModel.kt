package com.example.timerapp.ui.timer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.timerapp.ui.theme.defaultProgress
import com.example.timerapp.ui.theme.defaultTimerValue
import com.example.timerapp.ui.theme.duration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

/**
 *  View Model for timer start, stop, pause and progress
 */
class TimerViewModel : ViewModel() {

    private var job: Job? = null

    // timer value updates periodically
    private val _timerValue = MutableStateFlow(defaultTimerValue)
    val timerValue = _timerValue.asStateFlow()

    // progress indicator
    private val _progress = MutableStateFlow(defaultProgress)
    val progress = _progress.asStateFlow()

    // play/pause timer button listener
    private val _play = MutableStateFlow(false)
    val play = _play.asStateFlow()

    // pause the timer
    fun pauseTimer() {
        job?.cancel()
        _play.value = false
    }

    // stop the timer
    fun stopTimer() {
        job?.cancel()
        _timerValue.value = 0
        _progress.value = 0.0f
        _play.value = true
    }

    // start the timer
    fun startTimer(totalTime: Int = defaultTimerValue) {
        when (_timerValue.value) {
            0 -> _timerValue.value = totalTime
        }
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            delay(timeMillis = 200)
            while (isActive) {
                when {
                    _timerValue.value <= 0 -> {
                        job?.cancel()
                        _play.value = false
                        return@launch
                    }

                    else -> {
                        delay(timeMillis = duration)
                        _timerValue.value -= 1
                        _progress.value = _timerValue.value.toFloat() / totalTime.toFloat()
                        _play.value = true
                    }
                }
            }
        }
    }
}
