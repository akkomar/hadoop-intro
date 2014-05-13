name := "Lesson 3"

version := "1.0"

scalaVersion := "2.10.3"

libraryDependencies += "org.apache.spark" %% "spark-core" % "0.9.1"

resolvers += "Akka repository" at "http://repo.akka.io/releases"

libraryDependencies += "org.apache.hadoop" % "hadoop-client" % "1.2.1"

//running:
//sbt package
//sbt run
