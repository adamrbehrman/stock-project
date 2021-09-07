package stocksproject

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.DataFrame

object Helpers {
  def prnt(strs:Seq[Any]) = {
    print(strs.mkString(" | ") + "\n")
  }
}

object StockAnalysis {
	import Helpers._

  def main(args:Array[String]):Unit = {
		val spark:SparkSession = SparkSession
  		.builder()
	  	.appName("Project-3")
      .master("local")
//		  .config("spark.master", "yarn-cluster")
      .getOrCreate()

    // Hide "INFO: blah..." stuff
    spark.sparkContext.setLogLevel("ERROR")
    
    import spark.implicits._
  
    val StockDF = createDF(spark)
  		
    StockDF.show(10, false)
  
    basicStats(spark, StockDF)

    spark.stop()
  }
  
  /**
    Creates the base DataFrame for analysis.
  */
  def createDF(spark:SparkSession) : DataFrame = {
		spark.read.format("csv")
      .option("inferSchema","true")
      .option("header","true")
      .load("hdfs://localhost:9000/stock-data/*.csv")
      .withColumn("filename",input_file_name)
      .withColumn("Company", reverse(split(col("filename"),"/")).getItem(0))
  }

  /**
    Basic stats.
  */
  def basicStats(spark:SparkSession, df:DataFrame) = {
    // DataFrame size
    prnt(Seq("DataFrame size",df.count()))

    // DataFrame composition
    df.groupBy("Company").count.show(10, false)

    
  }
}
