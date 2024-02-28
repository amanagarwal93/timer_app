package com.example.timerapp.ui.timer

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.timerapp.ui.theme.TimerAppTheme
import com.example.timerapp.ui.theme.defaultProgress
import com.example.timerapp.ui.theme.defaultTimerValue
import com.example.timerapp.ui.useCases.TimerDisplay
import com.example.timerapp.utils.requestNotificationPermissionDialog
import com.example.timerapp.utils.showNotification

/**
 * Activity responsible for showing UI from TimerDisplay &
 * showing notification when the timer finishes
 */

class TimerActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            TimerAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // view model is getting injected by koin
                    val viewModel: TimerViewModel = viewModel()
                    val progress = viewModel.progress.collectAsState().value
                    val play = viewModel.play.collectAsState().value
                    val timerValue = viewModel.timerValue.collectAsState().value

                    TimerDisplay(
                        timerValue = timerValue,
                        play = play,
                        progress = progress,
                        start = { viewModel.startTimer() },
                        pause = { viewModel.pauseTimer() },
                        stop = { viewModel.stopTimer() }
                    )
                    // show notification when timer is finished
                    when {
                        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> when {
                            requestNotificationPermissionDialog() -> displayNotification(timerValue)
                        }

                        else -> displayNotification(timerValue)
                    }
                }
            }
        }
    }

    // display notification in background
    private fun displayNotification(timerValue: Int) {
        when (timerValue) {
            0 -> showNotification(this@TimerActivity)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TimerAppTheme {
        TimerDisplay(
            timerValue = defaultTimerValue,
            play = true,
            start = {},
            progress = defaultProgress,
            pause = {}
        ) {
        }
    }
}
