package com.example.timerapp.domain

import com.example.timerapp.ui.timer.TimerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *  Module for creating an object for view model
 */

val appModule = module {
    viewModel { TimerViewModel() }
}
