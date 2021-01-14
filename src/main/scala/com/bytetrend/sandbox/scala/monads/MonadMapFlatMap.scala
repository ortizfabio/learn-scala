package com.bytetrend.sandbox.scala.monads

/**
  * https://freecontent.manning.com/using-option-in-scala-part-2-map-and-flatmap/
  *
  */
object MonadMapFlatMap extends App {

  val p1 = Person("Mary", 33, Some("M38974392743"))
  val p2 = Person("John", 34, None)
  val c1 = Car("Cube", Some(p1), Some("VDUY67"))
  val c2 = Car("X3", None, None)

  def ownerName(car: Car): Option[String] = car.owner.map(p => p.name)

  def extractRegistrationPlate(car: Car): Option[String] = car.registrationPlate.map(r => r)

  def ownerDrivingLicense(car: Car):Option[String] = car.owner.flatMap(_.drivingLicense)

  def optionOwnerDrivingLicense(optCar : Option[Car]):Option[String] = optCar.flatMap{ car =>
    car.owner.flatMap(person => person.drivingLicense)
  }

  case class Person(name: String, age: Int, drivingLicense: Option[String])

  case class Car(model: String, owner: Option[Person], registrationPlate: Option[String])

  println(s"Owner of ${c1} is ${ownerName(c1).getOrElse("Nobody")}")
  println(s"owner of ${c2} is  ${ownerName(c2).getOrElse("Nobody")}")

  println(s"Plate of ${c1} is ${extractRegistrationPlate(c1).getOrElse("None")}")
  println(s"Plate of ${c2} is  ${extractRegistrationPlate(c2).getOrElse("None")}")

  println(s"Plate of ${c1} is ${ownerDrivingLicense(c1).getOrElse("None")}")
  println(s"Plate of ${c2} is  ${ownerDrivingLicense(c2).getOrElse("None")}")

  println(s"Plate of ${c1} is ${optionOwnerDrivingLicense(Some(c1)).getOrElse("None")}")
  println(s"Plate of ${c2} is  ${optionOwnerDrivingLicense(None).getOrElse("None")}")

}
