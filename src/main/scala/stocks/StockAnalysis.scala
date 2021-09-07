package stocksproject

import org.apache.spark.sql.SparkSession

object StockAnalysis {
	def main(args:Array[String]):Unit = {
		val spark:SparkSession = SparkSession
  		.builder()
	  	.appName("Project-3")
      .master("local")
//		  .config("spark.master", "yarn-cluster")
//  		.config("spark.broadcast.compress", "false")
//	  	.config("spark.option", "value")
		.getOrCreate()

		import spark.implicits._

		val df = spark.read.format("csv").option("inferSchema","true").option("header","true").load("hdfs://localhost:9000/stock-data/Google.csv")

		df.printSchema
		
		spark.stop()
	}
}
