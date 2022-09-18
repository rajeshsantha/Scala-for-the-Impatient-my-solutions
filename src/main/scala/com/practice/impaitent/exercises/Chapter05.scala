package com.practice.impaitent.exercises

import scala.beans.BeanProperty

/** *
 * Classes
 */
object Chapter05 {
  def main(args: Array[String]): Unit = {

  }

  /*
    1. Improve the Counter class in Section 5.1, “Simple Classes and Parameterless Methods,” on page 55 so that it doesn’t turn negative at Int.MaxValue.
  */

  class Counter(private var value: Int = 0) {

    def increment() {
      if (value != Int.MaxValue) value += 1
      //else value += 0
    } // Methods are public by default
    def current: Int = value
  }

  /*
  2. Write a class BankAccount with methods deposit and withdraw, and a read-only property balance.
  */

  class BankAccount(var balance: Double = 0.0) {
    def withDraw(inputAmount: Double): Unit = {
      if (currentBalance < inputAmount)
        println("do not have sufficient balance, Please enter correct amount")
      else
        balance -= inputAmount
    }

    def deposit(inputAmount: Double): Unit = {
      balance += inputAmount
    }

    def currentBalance: Double = balance
  }

  /*
  3. Write a class Time with read-only properties hours and minutes and a method before(other: Time): Boolean
  that checks whether this time comes before the other. A Time object should be constructed as new Time(hrs, min), where hrs is in military time format (between 0 and 23).
  */

  class Time(val hrs: Int, val mins: Int) {

    if (hrs > 23 || hrs < 0) throw new IllegalArgumentException(s"invalid time : Hours ($hrs) should be between 0 to 23")
    else hrs

    if (mins > 59 || mins < 0) throw new IllegalArgumentException(s"invalid time : mins ($mins) should be between 0 to 59")
    else mins

    def before(other: Time): Boolean = {
      (hrs, mins) match {
        case (hours, _) if hours < other.hrs => true
        case (hours, minutes) if hours == other.hrs && minutes < other.mins => true
        case (_, _) => false
      }
    }
  }

  /*
  4. Reimplement the Time class from the preceding exercise so that the internal representation is the number of minutes since midnight (between 0 and 24 × 60 – 1). Do not change the public interface. That is, client code should be unaffected by your change.
  */


  /*
  5. Make a class Student with read-write JavaBeans properties name (of type String) and id (of type Long). What methods are generated? (Use javap to check.) Can you call the JavaBeans getters and setters in Scala? Should you?
  */
  class Student(@BeanProperty var name: String) {

    //Generates below 4 methods
    /*
    1. name:String
    2. name_=(newValue:String):Unit
    3. getName():String
    4. setName(newValue:String):Unit // because its var

*/


  }

  /*
  6. In the Person class of Section 5.1, “Simple Classes and Parameterless Methods,” on page 55, provide a primary constructor that turns negative ages to 0.
  */
  class PersonX(var age: Int) {
    age = if (age < 0) 0 else age //This line is part of constructor only. All the class body written lines will execute when new object is created. So you can do all this kind of validations.

    //new PersonX(-10).age => 0
    //new PersonX(34).age => 34


  }


  /*
  7. Write a class Person with a primary constructor that accepts a string containing a first name, a space, and a last name, such as new Person("Fred Smith"). Supply read-only properties firstName and lastName. Should the primary constructor parameter be a var, a val, or a plain parameter? Why?
  */
  class Person(val fullName: String) {
    val (firstName, lastName) = fullName.split(" ") //read only values So VALs

  }

  /*
  8. Make a class Car with read-only properties for manufacturer, model name, and model year, and a read-write property for the license plate. Supply four constructors. All require the manufacturer and model name. Optionally, model year and license plate can also be specified in the constructor. If not, the model year is set to -1 and the license plate to the empty string. Which constructor are you choosing as the primary constructor? Why?
  */
  //     new Car("Toyota","Fortuner")
  class Car(val manufacturer: String, val modelName: String) // primary constructor
  {
    var licensePlate: String = "" //default values
    var modelYear: Int = -1


    def this(manufacturer: String, modelName: String, modelYear: Int) {
      this(manufacturer, modelName)
      this.modelYear = modelYear

    }

    def this(manufacturer: String, modelName: String, licensePlate: String) {
      this(manufacturer, modelName)
      this.licensePlate = licensePlate

    }


    def this(manufacturer: String, modelName: String, modelYear: Int, licensePlate: String) {
      this(manufacturer, modelName)
      this.modelYear = modelYear
      this.licensePlate = licensePlate

    }

    new Car("Toyota", "Fortuner")
    new Car("Toyota", "Fortuner", 2016)
    new Car("Toyota", "Fortuner", " TS01AB0123")
    new Car("Toyota", "Fortuner", 2016, " TS01AB0123")
  }

  /*
  9. Reimplement the class of the preceding exercise in Java, C#, or C++ (your choice). How much shorter is the Scala class?
  */

  /*
  10. Consider the class
  class Employee(val name: String, var salary: Double) {
    def this() { this("John Q. Public", 0.0) }
  }
  Rewrite it to use explicit fields and a default primary constructor. Which form do you prefer? Why?
*/

  class Employee(val name: String = "John Q. Public", var salary: Double = 0.0)

}
