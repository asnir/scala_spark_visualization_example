import org.apache.spark.sql.{DataFrame, Dataset, Row}


/**
  * This class Analyze crimes count by type & district.
  */
object CrimeAnalyzer extends Analyzer {

  private val dataCleansingFilter = config.getString("app.dataCleansing.filter")

  /**
    * @inheritdoc
    */
  override def dataCleansing(df: DataFrame): Dataset[Row] = {
    df.filter(dataCleansingFilter)
  }

  /**
    * @inheritdoc
    */
  override def analyze(df: DataFrame): Dataset[Row] = {
    val statisticsDF = df.groupBy(category, subCategory)
      .count().orderBy(category, subCategory)
    statisticsDF
  }

  def main(args: Array[String]) {
    val (inputDir: String, outputDir: String, appName: String) = argsParser(args)
    run(inputDir, outputDir, appName)

  }

  private def argsParser(args: Array[String]) = {
    assert(args.length == 3)
    val inputDir = args(0)
    val outputDir = args(1)
    val appName = args(2)

    (inputDir, outputDir, appName)
  }

}
