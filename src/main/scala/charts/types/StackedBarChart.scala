package charts.types

import vegas.DSL.ExtendedUnitSpecBuilder
import vegas._
import vegas.sparkExt._


/**
  * This class represent <a href ="https://vega.github.io/vega-lite/examples/bar_grouped.html">Grouped Bar Chart </a>
  */
object StackedBarChart extends Chart {
  /**
    * The method create <a href ="https://vega.github.io/vega-lite/examples/bar_grouped.html">Grouped Bar Chart </a>
    *
    * @param dataVisualizer The paint meta data object.
    * @return The paint object.
    */
  override def create(dataVisualizer: DataVisualizer): ExtendedUnitSpecBuilder = {
    Vegas(dataVisualizer.title)
      .withDataFrame(dataVisualizer.df)
      .mark(Bar)
      .encodeX(dataVisualizer.category, Nominal)
      .encodeY(dataVisualizer.value, Quantitative)
      .encodeColor(dataVisualizer.subCategory, Nominal)
  }
}
