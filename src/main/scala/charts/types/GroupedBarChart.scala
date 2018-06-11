package charts.types

import vegas.DSL.ExtendedUnitSpecBuilder
import vegas._
import vegas.sparkExt._


/**
  * This class represent <a href ="https://vega.github.io/vega-lite/examples/bar_grouped.html">Grouped Bar Chart </a>
  */
object GroupedBarChart extends Chart {


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
      .encodeColumn(dataVisualizer.category,
        Nominal,
        scale = Scale(padding = 12.toDouble),
        axis = Axis(orient = Orient.Bottom, axisWidth = 1.toDouble,
          offset = (-8).toDouble)
      )
      .encodeY(dataVisualizer.value, Quantitative, axis = Axis(grid = false))
      .encodeX(dataVisualizer.subCategory, Nominal, scale = Scale(padding = 12.toDouble),
        axis = Axis(offset = 12.toDouble, tickPadding = 12.toDouble))
      .encodeColor(dataVisualizer.subCategory, Nominal)
  }
}
