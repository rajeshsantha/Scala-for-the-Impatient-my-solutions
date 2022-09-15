package com.practice.impaitent.exercises

import java.awt.datatransfer._

object Chapter02 extends App {

  /**
   *
   *1. Write a code snippet that sets a to an array of n random integers between 0 (inclusive) and n (exclusive).
   */
  def randomInclusiveArray(n: Int): Unit = Array[Int](n).map(_ => scala.util.Random.nextInt(n))

  /**
   * 2. Write a loop that swaps adjacent elements of an array of integers. For example, Array(1, 2, 3, 4, 5) becomes Array(2, 1, 4, 3, 5).
   *
   */
  def swapArray(arr: Array[Int]): Array[Int] = {
    //  val arr = Array(1, 2, 3, 4, 5)
    /*
     // Array(1, 2, 3, 4, 5) // input
     // Array(2, 1, 4, 3, 5) // output
     // Array(0, 1, 2, 3, 4) // index
      arr.grouped(2).toArray.map(x=>x.reverse).flatten
     */

    val len = arr.length - 1
    for (i <- 0 until len if i % 2 == 0) {
      val temp = arr(i)
      arr(i) = arr(i + 1)
      arr(i + 1) = temp
    }
    arr
  }

  /**
   *
   * 3. Repeat the preceding assignment, but produce a NEW array with the swapped values. Use for/yield.
   */

  def createNewSwappedArray(a: Array[Int]): Array[Int] = {
    //val newArr =  arr.grouped(2).toArray.flatMap(_.reverse)
    // Array(1, 2, 3, 4, 5) // input
    (for (index <- a.indices) yield
      index match {
        case _ if index % 2 == 0 && a.length == index => a(index)
        // case for last odd element Eg: for "5" in arr, value should not change.
        case _ if index % 2 == 0 => a(index + 1)
        // case for even number indices , which will be swapped. Eg: for values 1,3 (not indices, VALUES in array)
        case _ => a(index - 1)
        // case for odd indices, which will be swapped with even indices Eg: for values 2,4 (not indices, VALUES in array)
      }
      ).toArray
  }

  /**
   *
   * 4. Given an array of integers, produce a new array that contains all positive values of the original array, in their original order, followed by all values that are zero or negative, in their original order.
   */
  def splitArrayWithOrder(arr: Array[Int]): (Array[Int], Array[Int]) = {

    val positiveArray = for (i <- arr if i > 0) yield i
    val negativeArray = for (i <- arr if i <= 0) yield i
    (positiveArray, negativeArray)

    /*
     OR simply write one liner as below

    val (pArr, nArr) = arr.partition(_ > 0)
    (pArr, nArr)
*/
  }

  /**
   *
   * 5. How do you compute the average of an Array[Double]?
   */
  def avgArray(arr: Array[Int]): Int = arr.sum / arr.length

  /**
   * 6. How do you rearrange the elements of an Array[Int] so that they appear in
   * reverse sorted order? How do you do the same with an ArrayBuffer[Int]?
   */
  def reArrangeArray(arr: Array[Int]): Array[Int] = {
    // one liner =>  arr.sortWith(_ > _) // extra memory ; returning new array
    val len = arr.length
    for (i <- 0 until len / 2) {
      val temp = arr(i)
      arr(i) = arr(len - 1 - i)
      arr(len - 1 - i) = temp
    }
    arr // returning same array ; In place ; no extra memory
  }

  /**
   * 7. Write a code snippet that produces all values from an array with duplicates
   * removed. (Hint: Look at Scaladoc.)
   */
  def removeDuplicates(arr: Array[Int]): Array[Int] = arr.distinct

  /** *
   * 8. Suppose you are given an array buffer of integers and want to remove all but the first negative number. Here is a sequential solution that sets a flag when the first negative number is called, then removes all elements beyond.
   * var first = true var n = a.length vari=0 while(i<n){
   * if(a(i)>=0)i+=1 else {
   * if(first){first=false;i+=1}
   * else { a.remove(i); n -= 1 }
   * }
   * }
   */
  def removeFirstNegativeNumber(arr: Array[Int]): Array[Int] = {
    (for (i <- arr.indices if i != arr.indexWhere(_ < 0)) yield arr(i)).toArray
  }

  /**
   * 9. Improve the solution of the preceding exercise by collecting the positions that should be moved and their target positions.
   * Make those moves and truncate the buffer. Don’t copy any elements before the first unwanted element.
   */
  def removeFirstNegativeNumber_part2(arr: Array[Int]): Array[Int] = {

    val negativeIndices = (for (i <- arr.indices if arr(i) < 0) yield i).toArray // collected negative value positions.

    // question contraints are unclear . --  To be solved --

    arr
  }

  /** *
   * 10. Makeacollectionofalltimezonesreturnedbyjava.util.TimeZone.getAvailableIDs that are in America.
   * Strip off the "America/" prefix and sort the result.
   */
  def sortTimeZones(): Unit = {
    java.util.TimeZone
      .getAvailableIDs
      .filter(_.contains("America"))
      .map(_.replace("America/", ""))
      .sorted
  }

  /** *
   *
   * 11. Import java.awt.datatransfer._ and make an object of type SystemFlavorMap with the call
   * val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
   * Then call the getNativesForFlavor method with parameter DataFlavor.imageFlavor and get the return value as a Scala buffer.
   * (Why this obscure class? It’s hard to find uses of java.util.List in the standard Java library.)
   */

  def javaScalaInteroperability(): java.util.List[String] = {
    java.awt.datatransfer.SystemFlavorMap
      .getDefaultFlavorMap.asInstanceOf[SystemFlavorMap]
      .getNativesForFlavor(DataFlavor.imageFlavor) //[PNG, JFIF, TIFF]
  }
}
