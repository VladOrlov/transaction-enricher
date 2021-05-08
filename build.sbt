name := "transaction-enricher"

version := "0.1"

scalaVersion := "2.12.13"

libraryDependencies += "org.apache.spark" %% "spark-core" % "3.1.1"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.1.1"
libraryDependencies += "org.apache.spark" %% "spark-streaming" % "3.1.1" /*% "provided"*/
libraryDependencies += "org.apache.spark" %% "spark-sql-kafka-0-10" % "3.1.1"