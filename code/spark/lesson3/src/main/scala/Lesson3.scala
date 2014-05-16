/** * SimpleApp.scala ***/

import org.apache.spark.rdd.RDD
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

object Lesson3 {

  def main(args: Array[String]) {
    solvePart1
    solvePart2
  }

  def solvePart2 = {
    val accessLogFile = "/home/training/udacity_training/data/access_log"
    val sc = new SparkContext("local", "Access Log", "/home/training/spark-0.9.1-bin-hadoop1/",
      List("target/scala-2.10/lesson-3_2.10-1.0.jar"))
    val accessLog = sc.textFile(accessLogFile)

    val numOfHits = accessLog.filter(_.contains("\"GET /assets/js/the-associates.js")).count()
    val ipHits = accessLog.filter(_.startsWith("10.99.99.186")).count()

    println("Number of hits to 'the-associates.js':")
    println(numOfHits)

    println("Number of hits by IP: 10.99.99.186:")
    println(ipHits)


  }

  def solvePart1 = {
    val purchasesFile = "/home/training/udacity_training/data/purchases.txt"
    val sc = new SparkContext("local", "Purchases", "/home/training/spark-0.9.1-bin-hadoop1/",
      List("target/scala-2.10/lesson-3_2.10-1.0.jar"))

    val purchasesData = sc.textFile(purchasesFile) //.sample(false, 0.01, 1)

    val toysAndElectronicsSales = computeTotalSalesForToysAndElectronics(purchasesData)
    val highestSalesPerStore = computeHighestSaleForSelectedStores(purchasesData)
    val totalSales = computeTotalSales(purchasesData)

    println("Total sales for Toys and Consumer Electronics:")
    toysAndElectronicsSales.foreach(println)

    println("Highest sales for selected stores:")
    highestSalesPerStore.foreach(println)

    println("Total sales:")
    totalSales.foreach(println)
  }

  def computeTotalSalesForToysAndElectronics(rawData: RDD[String]): Array[(String, BigDecimal)] = {
    rawData
      .map(line => line.split("\t"))
      .filter(purchase => "Toys" == purchase(3) || "Consumer Electronics" == purchase(3))
      .map(purchase => (purchase(3), scala.math.BigDecimal(purchase(4))))
      .reduceByKey((a: BigDecimal, b: BigDecimal) => a + b)
      .collect()
  }

  def computeHighestSaleForSelectedStores(rawData: RDD[String]): Array[(String, BigDecimal)] = {
    rawData
      .map(line => line.split("\t"))
      .map(line => (line(2), BigDecimal(line(4))))
      .reduceByKey((a: BigDecimal, b: BigDecimal) => if (a > b) a else b)
      .filter((t: (String, BigDecimal)) => "Reno" == t._1 || "Toledo" == t._1 || "Chandler" == t._1)
      .collect()
  }

  def computeTotalSales(rawData: RDD[String]): Array[(String, BigDecimal)] = {
    val numberOfSales = BigDecimal(rawData.count())
    val totalSalesVal = rawData
      .map(line => line.split("\t"))
      .map(line => (BigDecimal(line(4))))
      .reduce((a, b) => a + b)
    Array(("Number of sales", numberOfSales), ("Total value of sales", totalSalesVal))
  }

}

