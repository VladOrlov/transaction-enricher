package com.jvo

import org.apache.spark.sql.SparkSession


object TransactionEnricher {


  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder()
      .appName("Transaction Enricher")
      .master("local[2]")
      .getOrCreate()

    sparkSession.sparkContext.setLogLevel("ERROR")

    import sparkSession.implicits._

    val df = sparkSession
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "general_wallet_transactions")
      .load()

    df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
      .as[(String, String)]

//    val transactionStream = spark
//      .readStream
//      .format("kafka")
//      .option("kafka.bootstrap.servers", "localhost:9092")
//      .option("subscribe", "general_wallet_transactions")
//      .load()

    val finalization = df
      .writeStream
      .format("console")
      .start() // старт нашего стриминга, без этого ничего не поедет

    finalization.awaitTermination() // а без этого ничего не будет длится

  }

}
