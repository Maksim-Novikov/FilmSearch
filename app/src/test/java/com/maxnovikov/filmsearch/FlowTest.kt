package com.maxnovikov.filmsearch

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
  runBlocking {
    launch {
      sampleFlow().flowOn(Dispatchers.IO).collect {
        println(it)
      }
    }
    launch {
      sampleFlow().flowOn(Dispatchers.IO).collect {
        println(it)
      }
    }
  }
}

fun sampleLiveDataVsStateFlow(): Flow<Int> {

  val liveData = MutableLiveData(0)
  liveData.value = null

  val firstFlow = MutableStateFlow<Int>(0)
  firstFlow.tryEmit(0)
  return firstFlow
}

fun sampleFlow(): Flow<Int> {

  return flow<Int> {
    println(Thread.currentThread())
    (0..10).forEach {
      emit(it * 10)
      delay(1000)
    }
  }
}