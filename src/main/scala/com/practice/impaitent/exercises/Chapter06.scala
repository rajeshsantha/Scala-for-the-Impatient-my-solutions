package com.practice.impaitent.exercises

object Chapter06 {
  def main(args: Array[String]): Unit = {
    /**
     * 1. Write an object Conversions with methods inchesToCentimeters,
     * gallonsToLiters, and milesToKilometers.
     **/
    println(Conversions.inchesToCentimeters(10))
    println(Conversions.gallonsToLiters(10))
    println(Conversions.milesToKilometers(10))
    println(Conversions.milesToKilometers(10))
println(args.reverse.mkString(" "))
  }
}

object Conversions {
  def inchesToCentimeters(inches: Double): Double = inches * 2.54

  def gallonsToLiters(gallons: Double): Double = gallons * 3.78541

  def milesToKilometers(miles: Double): Double = scala.math.round(miles * 1.60934)
}

/**
 * 2. The preceding problem wasn’t very object-oriented.
 * Provide a general superclass UnitConversion and define objects InchesToCentimeters, GallonsToLiters, and MilesToKilometers that extend it.
 */

trait UnitConversion {
  def genericConvertFunc(inches: Double): Double
}

object InchesToCentimeters extends UnitConversion {
  override def genericConvertFunc(inches: Double): Double = inches * 2.54
}

object GallonsToLiters extends UnitConversion {
  override def genericConvertFunc(gallons: Double): Double = gallons * 3.785
}

object MilesToKilometers extends UnitConversion {
  override def genericConvertFunc(miles: Double): Double = miles / 0.62137
}

/** *
 * 3. Define an Origin object that extends java.awt.Point. Why is this not actually a good idea? (Have a close look at the methods of the Point class.)
 */
object Origin extends java.awt.Point {
  //Why is this not actually a good idea? (Have a close look at the methods of the Point class.)
  // Ans : java.awt.Point class is mutable class
}

/** *
 *4. Define a Point class with a companion object so that you can construct Point instances as Point(3, 4), without using new.
 */
object Point {
  def apply(p1: Int, p2: Int): Point = new Point(p1, p2)
}

class Point(val p1: Int, val p2: Int)

/** *
 *5. Write a Scala application, using the App trait, that prints its command-line arguments in reverse order, separated by spaces.
 * For example, scala Reverse Hello World should print World Hello.
 */
/*
object Reverse extends App {
  println(args.reverse.mkString(" "))
}
 */