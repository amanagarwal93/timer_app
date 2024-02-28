package com.example.timerapp.utils

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.timerapp.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

/**
 * Run-time Permission for Notification
 */

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun requestNotificationPermissionDialog(): Boolean {
    var isPermissionGranted = false
    val context = LocalContext.current

    val permissionState =
        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
    when {
        !permissionState.status.isGranted && permissionState.status.shouldShowRationale -> {
            // the permission denied once, so show the rationale dialog and request permission
            val openDialog = remember { mutableStateOf(true) }
            when {
                openDialog.value -> AlertDialog(
                    onDismissRequest = { openDialog.value = false },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                permissionState.launchPermissionRequest()
                                openDialog.value = false
                            }
                        )
                        { Text(text = context.getString(R.string.allow)) }
                    },
                    dismissButton = {
                        TextButton(
                            onClick =
                            { openDialog.value = false }
                        )
                        { Text(text = context.getString(R.string.dismiss)) }
                    },
                    title = { Text(text = context.getString(R.string.permission_title)) },
                    text = { Text(text = context.getString(R.string.permission_description)) },
                )

                permissionState.status.isGranted -> isPermissionGranted = true
                // permission denied forever
                else -> {
                    isPermissionGranted = false
                    LaunchedEffect(
                        key1 = Unit,
                        block = { permissionState.launchPermissionRequest() })
                }
            }
        }
    }
    return isPermissionGranted
}
