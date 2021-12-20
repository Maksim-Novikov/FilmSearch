package com.maxnovikov.filmsearch

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
//    launch(Dispatchers.Default) {
//      firstStateFlow().collect {
//        println(it)
//      }
//    }

    val flow = simpleFlow()
    println("create flow")
    delay(1000)
    println("waited")
    launch {
      flow.collect {
        println("${Thread.currentThread()} collect1")
        println(it)
      }
    }
    launch(Dispatchers.IO) {
      simpleFlow().flowOn(Dispatchers.Default).collect {
        println("${Thread.currentThread()} collect2")
        println(it)
      }
    }
  }
}

private fun simpleFlow(): Flow<Int> {
  return flow<Int> {
    println("${Thread.currentThread()} simpleFlow")
    (1..5).forEach {
      emit(it)
      delay(1000)
    }
  }
}

private fun firstStateFlow(): Flow<Int> {
//  val liveData = MutableLiveData<Int>(0)
//  val state1: Int? = liveData.value
//  liveData.value = 1

  val stateFlow = MutableStateFlow<Int>(0)
  val state2: Int = stateFlow.value
  stateFlow.value = 1
  stateFlow.value = 2
  stateFlow.value = 3
  stateFlow.value = 4

  return stateFlow

}