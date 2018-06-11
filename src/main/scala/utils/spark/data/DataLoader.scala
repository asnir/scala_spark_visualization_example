package utils.spark.data

import org.apache.spark.sql.{DataFrame, SparkSession}

trait DataLoader {

  /**
    * The method load data.
    * @param session It uses spark session to load the data.
    * @return The loaded data.
    */
  def load(session: SparkSession): DataFrame
}
