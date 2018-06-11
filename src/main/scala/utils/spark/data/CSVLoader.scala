package utils.spark.data

import org.apache.spark.sql.{DataFrame, SparkSession}

class CSVLoader(inputDir: String) extends DataLoader {
  
  /**
    * The method load data from CSV.
    * @param session It uses spark session to load the data.
    * @return The loaded data.
    */
  override def load(session: SparkSession): DataFrame = {
    val df = session.read.format("csv")
      .option("sep", ",")
      .option("inferSchema", "true")
      .option("header", "true")
      .load(inputDir)
    df
  }

}
