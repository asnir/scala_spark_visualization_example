package charts.viewers

import java.nio.file.{Files, Paths}

import vegas.DSL.ExtendedUnitSpecBuilder
import vegas.render.ShowHTML



object HtmlPaintViewer extends FileViewer {

  /**
    * The method save the plot object to an html file.
    * @param plot The plot object.
    * @param outputFile The full path of the output file.
    */
  override def show(plot: ExtendedUnitSpecBuilder, outputFile: String): Unit = {
    implicit val renderer: ShowHTML = vegas.render.ShowHTML(html => {
      Files.write(Paths.get(outputFile), html.getBytes())
    })
    plot.show
  }
}
