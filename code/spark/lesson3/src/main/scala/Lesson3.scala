/** * SimpleApp.scala ***/

import org.apache.spark.rdd.RDD
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

object Lesson3 {
  def main(args: Array[String]) {
    val purchasesFile = "/home/training/udacity_training/data/purchases.txt"
    val sc = new SparkContext("local", "Purchases", "/home/training/spark-0.9.1-bin-hadoop1/",
      List("target/scala-2.10/lesson-3_2.10-1.0.jar"))

    val purchasesData = sc.textFile(purchasesFile)//.sample(false, 0.01, 1)

    val toysAndElectronicsSales = computeTotalSalesForToysAndElectronics(purchasesData)
    val highestSalesPerStore = computeHighestSaleForSelectedStores(purchasesData)

    println("Total sales for Toys and Consumer Electronics:")
    toysAndElectronicsSales.foreach(println)

    println("Highest sales for selected stores:")
    highestSalesPerStore.foreach(println)
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
      .map(line => (line(2),BigDecimal(line(4))))
      .reduceByKey((a: BigDecimal, b: BigDecimal) => if (a>b) a else b)
      .filter((t: (String, BigDecimal)) => "Reno"==t._1 || "Toledo"==t._1 || "Chandler"==t._1)
      .collect()
  }
}

