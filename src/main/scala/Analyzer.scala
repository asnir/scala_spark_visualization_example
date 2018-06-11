
import java.io.File

import charts.ChartsMapper
import charts.types.DataVisualizer
import charts.viewers.HtmlPaintViewer
import com.typesafe.config.{Config, ConfigFactory}
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}
import utils.spark.data.CSVLoader

abstract class Analyzer {
  protected val config: Config = ConfigFactory.load()
  protected val title: String = config.getString("app.view.title")
  protected val category: String = config.getString("app.columns.category")
  protected val subCategory: String = config.getString("app.columns.subCategory")
  protected val aggregatedColumn: String = config.getString("app.columns.aggregatedColumn")


  /**
    * This method create spark session.
    *
    * @param appName The application name.
    * @return Spark Session.
    */
  def createSparkSession(appName: String): SparkSession = {
    SparkSession
      .builder()
      .appName(appName)
      .getOrCreate()
  }

  /**
    * This method is running data-cleansing of the data.
    *
    * @param df The data-set.
    * @return Clean data set.
    */
  def dataCleansing(df: DataFrame): Dataset[Row]

  /**
    * The method analyze the data.
    *
    * @param df The data-set.
    * @return A new data-set.
    */
  def analyze(df: DataFrame): Dataset[Row]


  /**
    * The method save the data to a CSV file.
    *
    * @param df     The data-set.
    * @param output The output directory.
    */
  def save(df: Dataset[Row], output: String): Unit = {
    df.repartition(1)
      .write
      .option("header", value = true)
      .csv(output)
  }

  def visualData(data: DataVisualizer, outputFile: String): Unit = {
    ChartsMapper.getAvailableCharts.foreach { case (name, chart) =>
      HtmlPaintViewer.show(chart.create(data), "%s_%s.html".format(outputFile, name))
    }
  }


  /**
    * This strategy method is responsible on loading data from directory, cleaning & analyze it.
    * Afterwords, the method will save the results & visualization to files.
    *
    * @param inputDir  The input directory.
    * @param outputDir The output directory path.
    * @param appName   The application name.
    */
  def run(inputDir: String, outputDir: String, appName: String) {
    val session = createSparkSession(appName)
    val df: DataFrame = new CSVLoader(inputDir).load(session)
    val cleanDF = dataCleansing(df)
    val statisticsDF: Dataset[Row] = analyze(cleanDF)
    val output = "%s%s%s".format(outputDir, File.separator, appName)
    save(statisticsDF, output)
    visualData(DataVisualizer(statisticsDF, title, category, subCategory, aggregatedColumn), output)
  }


}
