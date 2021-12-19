package com.maxnovikov.filmSearch.di

import com.maxnovikov.filmSearch.data.local.FavoritesDao
import com.maxnovikov.filmSearch.data.local.FavoritesDaoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface FavoritesModule {

  @Binds
  @Singleton
  fun bindsFavoritesDao(favoritesDao: FavoritesDaoImpl): FavoritesDao
}