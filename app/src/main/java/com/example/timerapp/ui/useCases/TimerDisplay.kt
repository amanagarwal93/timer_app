package com.example.timerapp.ui.useCases

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.timerapp.R
import com.example.timerapp.ui.theme.TimerAppTheme
import com.example.timerapp.ui.theme.boxSize
import com.example.timerapp.ui.theme.cardSize
import com.example.timerapp.ui.theme.defaultProgress
import com.example.timerapp.ui.theme.defaultTimerValue
import com.example.timerapp.ui.theme.elevationHeight
import com.example.timerapp.ui.theme.noPadding
import com.example.timerapp.ui.theme.paddingLarge
import com.example.timerapp.ui.theme.paddingSmall
import com.example.timerapp.ui.theme.paddingValue
import com.example.timerapp.ui.theme.strokeWidth
import com.example.timerapp.ui.theme.textSize
import com.example.timerapp.ui.theme.timerColor
import com.example.timerapp.ui.theme.totalValue

/**
 * Compose UI for displaying Timer
 */

@Composable
fun TimerDisplay(
    timerValue: Int,
    play: Boolean,
    progress: Float,
    start: () -> Unit,
    pause: () -> Unit,
    stop: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            progress = progress,
            strokeWidth = strokeWidth,
            modifier = Modifier.size(boxSize)
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = getTimerLabel(timerValue),
                    fontSize = textSize,
                    color = timerColor,
                    modifier = Modifier.padding(noPadding, paddingLarge)
                )
                Row {
                    AnimatedVisibility(visible = play) {
                        ElevatedCard(
                            modifier = Modifier
                                .size(cardSize)
                                .padding(paddingSmall)
                                .clickable(onClick = { pause() }),
                            shape = CircleShape,
                            elevation = CardDefaults
                                .cardElevation(defaultElevation = elevationHeight),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.onPrimary
                            )
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Image(
                                    modifier = Modifier.align(Alignment.Center),
                                    painter = painterResource(id = R.drawable.ic_pause),
                                    contentDescription = null,
                                )
                            }
                        }
                    }

                    AnimatedVisibility(visible = !play) {
                        ElevatedCard(
                            elevation = CardDefaults
                                .cardElevation(defaultElevation = elevationHeight),
                            modifier = Modifier
                                .size(cardSize)
                                .padding(paddingSmall)
                                .clickable(onClick = { start() }),
                            shape = CircleShape,
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.onPrimary
                            )
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Image(
                                    modifier = Modifier.align(Alignment.Center),
                                    painter = painterResource(id = R.drawable.ic_play),
                                    contentDescription = null,
                                )
                            }
                        }
                    }

                    AnimatedVisibility(visible = play) {
                        ElevatedCard(
                            modifier = Modifier
                                .size(cardSize)
                                .padding(paddingSmall)
                                .clickable(onClick = { stop() }),
                            shape = CircleShape,
                            elevation = CardDefaults
                                .cardElevation(defaultElevation = elevationHeight),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.onPrimary
                            )
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Image(
                                    modifier = Modifier.align(Alignment.Center),
                                    painter = painterResource(id = R.drawable.ic_stop),
                                    contentDescription = null,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = false)
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

fun getTimerLabel(value: Int) = "${padding(value / totalValue)} " +
        ": ${padding(value % totalValue)}"

fun padding(value: Int) = if (value < paddingValue) ("0$value") else "" + value
