<img src="/timer_icon.png" alt="Timer logo"/>

# Timer Application

A simple timer application designed for demo. The timer is displayed and a notification popups when timer is finished.

## MVVM architecture with Jetpack Compose
- ui (for UI, UseCases, View Model)
- domain (for DI)
- utils (for extensive functions and utility tools)

# Instructions

- Launch the Application.
- Press the Play/Pause button to start/pause the Timer.
- Press stop button to stop the timer.
- Finishing/Stopping the timer will show a notification in background/foreground.

## Other useful features
- Dependency injection (with [Koin](https://insert-koin.io/))
- Google [Material Design](https://material.io/blog/android-material-theme-color) library
- Declarative UI (with [Jetpack Compose](https://developer.android.com/jetpack/compose))
- Run-time permissions (with [Accompanist](https://google.github.io/accompanist/permissions/#:~:text=A%20library%20which%20provides%20Android%20runtime%20permissions%20support%20for%20Jetpack%20Compose.&text=The%20permission%20APIs%20are%20currently,marked%20with%20the%20%40ExperimentalPermissionsApi%20annotation.))
- [Kotlin-Flow](https://kotlinlang.org/docs/flow.html)
- Edge To Edge Configuration

# Getting started

- Clone the repository in the Android Studio.
- On `androidApp/build.gradle`, update the dependencies as Android Studio suggests
- Run `./gradlew dependencyUpdates` and check for dependencies
- Ready to Use