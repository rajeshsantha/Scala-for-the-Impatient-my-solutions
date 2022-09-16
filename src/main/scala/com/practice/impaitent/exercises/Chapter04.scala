package com.practice.impaitent.exercises


import scala.collection.SortedMap

object Chapter04 {

  def main(args: Array[String]): Unit = {

    val gizmos = Map("MacBook" -> 130000, "Galaxy Smartphone" -> 67990, "tablet" -> 28000, "Zenbook Duo" -> 148000)
    val filePath = "/Users/rajeshsantha/Learning/temp/essay.txt"


    //Exercise 01
    println(discountedGizmos(gizmos))
    //Exercise 02
    println(wordCountByMutableMap(filePath))
    //Exercise 03
    println(wordCountByImmutableMap(filePath))
    //Exercise 04
    println(wordCountBySortedMap(filePath))
    //Exercise 05
    println(wordCountBySTreeMap(filePath))
    //Exercise 06
    getLinkedHashSet
    //Exercise 07
    printFormattedMap()
    //Exercise 08
    println(minmax(Array(1, 2, 3, 4, 5, 6, 7, 8, 9))) //(1,9)
    //Exercise 09
    println(lteqgt(Array(1, 2, 3, 4, 5, 6, 7, 8, 9), 5)) // (4,1,4)
    //Exercise 10
    // No output : Answer at Line number 192
  }

  /*
  1. Set up a map of prices for a number of gizmos that you covet. Then produce a second map with the same keys and the prices at a 10 percent discount.
   */
  // val gizmos = Map("MacBook"-> 130000, "Galaxy Smartphone"->67990, "tablet"-> 28000,"Zenbook Duo"-> 148000 )
  def discountedGizmos(prices: Map[String, Int]): Map[String, Double] =
  //prices.map { case (item, price) => (item, price - price * 0.1) }
    prices.map(x => (x._1, x._2 - x._2 * 0.1))


  /*
  2. Write a program that reads words from a file. Use a mutable map to count how often each word appears. To read the words, simply use a java.util.Scanner:
  val in = new java.util.Scanner(new java.io.File("myfile.txt")) while (in.hasNext()) process in.next()
  Or look at Chapter 9 for a Scalaesque way.
  At the end, print out all words and their counts.
  */

  def wordCountByMutableMap(filepath: String): scala.collection.mutable.Map[String, Int] = {
    val in = new java.util.Scanner(new java.io.File(filepath))
    val wordCount = scala.collection.mutable.Map[String, Int]()
    while (in.hasNext()) {
      val word = in.next() // get the word
        .stripPrefix("“") // remove “ from begining
        .stripPrefix("\"") // remove " from begining
        .stripSuffix("\"") // remove " from the end
        .replace(",", "") // remove ,
        .replace(".", "") // remove .
        .trim //remove space

      if (wordCount contains word) wordCount(word) += 1 // if word is already there, increase the counter by 1
      else wordCount(word) = 1 // else add the word entry
    }
    wordCount
  }

  //3. Repeat the preceding exercise with an immutable map.
  def wordCountByImmutableMap(filepath: String): scala.collection.immutable.Map[String, Int] = {
    val in = new java.util.Scanner(new java.io.File(filepath))
    var wordCount = scala.collection.immutable.Map[String, Int]()
    while (in.hasNext()) {
      val word = in.next() // get the word
        .stripPrefix("“") // remove “ from begining
        .stripPrefix("\"") // remove " from begining
        .stripSuffix("\"") // remove " from the end
        .replace(",", "") // remove ,
        .replace(".", "") // remove .
        .trim //remove space

      if (wordCount contains word)
        wordCount = wordCount + (word -> (wordCount(word) + 1))
      else
        wordCount = wordCount + (word -> 1)
    }
    wordCount
  }


  //4. Repeat the preceding exercise with a sorted map, so that the words are printed in sorted order.

  def wordCountBySortedMap(filepath: String): SortedMap[String, Int] = {
    val in = new java.util.Scanner(new java.io.File(filepath))
    var wordCount = SortedMap[String, Int]()
    while (in.hasNext()) {
      val word = in.next() // get the word
        .stripPrefix("“") // remove “ from begining
        .stripPrefix("\"") // remove " from begining
        .stripSuffix("\"") // remove " from the end
        .replace(",", "") // remove ,
        .replace(".", "") // remove .
        .trim //remove space

      if (wordCount contains word)
        wordCount = wordCount + (word -> (wordCount(word) + 1))
      else
        wordCount = wordCount + (word -> 1)
    }
    wordCount
  }


  //5. Repeat the preceding exercise with a java.util.TreeMap that you adapt to the Scala API.
  def wordCountBySTreeMap(filepath: String): java.util.TreeMap[String, Int] = {
    val in = new java.util.Scanner(new java.io.File(filepath))
    var wordCount = new java.util.TreeMap[String, Int]()
    while (in.hasNext()) {
      val word = in.next() // get the word
        .stripPrefix("“") // remove “ from begining
        .stripPrefix("\"") // remove " from begining
        .stripSuffix("\"") // remove " from the end
        .replace(",", "") // remove ,
        .replace(".", "") // remove .
        .trim //remove space

      if (wordCount.containsKey(word))
        wordCount.put(word, wordCount.get(word) + 1)
      else
        wordCount.put(word, 1)
    }
    wordCount
  }


  //6. Define a linked hash map that maps "Monday" to java.util.Calendar.MONDAY, and similarly for the other weekdays. Demonstrate that the elements are visited in insertion order.

  def getLinkedHashSet: scala.collection.mutable.LinkedHashMap[String, Int] = {
    import java.util.Calendar

    val days = scala.collection.mutable.LinkedHashMap(
      "SUNDAY" -> Calendar.SUNDAY, //1
      "MONDAY" -> Calendar.MONDAY, //2
      "TUESDAY" -> Calendar.TUESDAY, //3
      "WEDNESDAY" -> Calendar.WEDNESDAY, //4
      "THURSDAY" -> Calendar.THURSDAY, //5
      "FRIDAY" -> Calendar.FRIDAY, //6
      "SATURDAY" -> Calendar.SATURDAY //7
    )
    days
  }

  /*
      7. Print a table of all Java properties reported by the getProperties method of the java.lang.System class, like this:

  53
  java.runtime.name
  sun.boot.library.path
  java.vm.version
  java.vm.vendor
  java.vendor.url
  path.separator
  java.vm.name
  | Java(TM) SE Runtime Environment
  | /home/apps/jdk1.6.0_21/jre/lib/i386 | 17.0-b16
  | Sun Microsystems Inc.
  | http://java.sun.com/
  |:
  | Java HotSpot(TM) Server VM
  You need to find the length of the longest key before you can print the table.
  */

  def printFormattedMap(): Unit = {
    import scala.collection.JavaConverters._
    val properties = asScala(System.getProperties)
    val maxPropertyLength = properties.keys.map(_.length).max
    properties.foreach { case (key, value) =>
      val whiteSpace = " "
      println(s" property: $key ${whiteSpace * (maxPropertyLength - key.length)} | value: $value")
    }
  }

  /*
8. Write a function minmax(values: Array[Int]) that returns a pair containing the smallest and the largest values in the array.
 */
  def minmax(values: Array[Int]): (Int, Int) = (values.min, values.max)

  //9. Write a function lteqgt(values: Array[Int], v: Int) that returns a triple containing the counts of values less than v, equal to v, and greater than v.
  def lteqgt(values: Array[Int], v: Int): (Int, Int, Int) = (values.count(_ < v), values.count(_ == v), values.count(_ > v))


  //10. Whathappenswhenyouziptogethertwostrings,suchas"Hello".zip("World")? Come up with a plausible use case.

  // ANS : Vector((H,W), (e,o), (l,r), (l,l), (o,d))
  // usecase : To find the character by character comparision. H with W, e with o..etc
}
