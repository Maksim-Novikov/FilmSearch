package com.maxnovikov.filmSearch.presentation

import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import javax.inject.Inject
import javax.inject.Singleton

fun main() {
  val component: CarComponent = DaggerCarComponent.create()
  val car = component.car
  println(car.toString())
  val car1 = component.car
  println(car1.toString())
  val engine = component.engine
  println(engine.toString())

  val garage = Garage()
  component.inject(garage)
  val car3 = garage.car
  println(car3.toString())
  println(car1.engine.toString())
}

@Component(modules = [CarModule::class])
@Singleton
interface CarComponent {

  fun inject(garage: Garage)

  val car: Car

  val engine: Engine
}

@Module
@DisableInstallInCheck
class CarModule {

  @Provides
  @Singleton
  fun provideCar(engine: Engine): Car = Car(engine)

  @Provides
  fun provideCylinder1(): Cylinder1 = Cylinder1()
}

class Car(
  val engine: Engine
)

@Singleton
class Engine @Inject constructor(
  val cylinder1: Cylinder1,
  val cylinder2: Cylinder2,
  val cylinder3: Cylinder3
)

class Cylinder1()
class Cylinder2 @Inject constructor()
class Cylinder3 @Inject constructor()

class Garage {

  @Inject
  lateinit var car: Car
}