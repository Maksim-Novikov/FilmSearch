package com.maxnovikov.filmSearch.presentation.common

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun CoroutineScope.launchWithErrorHandler(
  onError: (Throwable) -> Unit = {},
  block: suspend () -> Unit
): Job {
  return launch(CoroutineExceptionHandler { _, throwable ->
    Log.e("launchWithErrorHandler", throwable.message, throwable)
    onError(throwable)
  }) {
    block()
  }
}