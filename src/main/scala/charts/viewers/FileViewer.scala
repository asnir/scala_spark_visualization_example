package charts.viewers

import vegas.DSL.ExtendedUnitSpecBuilder

trait FileViewer extends ChartViewer {

  /**
    * The method save the chart object to file.
    *
    * @param plot       The plot object.
    * @param outputFile The full path of the output file.
    */
  def show(plot: ExtendedUnitSpecBuilder, outputFile: String)
}
