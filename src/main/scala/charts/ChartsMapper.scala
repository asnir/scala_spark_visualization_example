package charts

import charts.types.Chart
import com.typesafe.config.ConfigFactory

import scala.collection.JavaConverters._
import scala.reflect.runtime.universe
import scala.util.Try

/**
  * This class load Charts dynamically from a config file.
  */
object ChartsMapper {
  private val runtimeMirror = universe.runtimeMirror(getClass.getClassLoader)

  /**
    * Create a chart object from class name.
    * @param clazz chart class name.
    * @return new instance of the chart.
    */
  private def resolveChart(clazz: String) = {
    val module = runtimeMirror.staticModule(clazz)
    val obj = runtimeMirror.reflectModule(module)
    obj.instance.asInstanceOf[Chart]
  }

  /**
    * Create a mapper between chart name to it's instance class.
    */
  private val resolver = ConfigFactory.load("charts.conf").getConfig("classMapper")
    .entrySet().asScala.map { e =>
    val key = e.getKey
    val chart = resolveChart(e.getValue.unwrapped().toString)
    (key, chart)
  }.toMap


  /**
    * This method get a chart name and return a new instance of this chart.
    * @param chartName This name will be used to decide, which chart we need to load.
    * @return The chart object.
    */
  def getChart(chartName: String): Try[Chart] = Try {
    resolver.get(chartName) match {
      case None => throw new NoSuchElementException(s"Chart not found for chartName: $chartName")
      case Some(chart) => chart
    }
  }

  /**
    * This method return the Available charts.
    * @return Available charts
    */
  def getAvailableCharts: Map[String, Chart] = {
    resolver.seq
  }
}
