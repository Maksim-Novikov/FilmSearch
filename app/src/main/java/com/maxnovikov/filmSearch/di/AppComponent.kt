package com.maxnovikov.filmSearch.di

import com.maxnovikov.filmSearch.presentation.topFilms.TopFilmsFragment
import dagger.Component

@Component(modules = [NetworkModule::class])
interface AppComponent {

  fun inject(topFilmsFragment: TopFilmsFragment)

}