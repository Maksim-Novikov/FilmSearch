package com.maxnovikov.filmSearch

import android.app.Application
import com.maxnovikov.filmSearch.di.AppComponent
import com.maxnovikov.filmSearch.di.DaggerAppComponent

class App : Application() {

  override fun onCreate() {
    super.onCreate()
    component = DaggerAppComponent.create()
  }

  companion object {

    lateinit var component: AppComponent
  }
}