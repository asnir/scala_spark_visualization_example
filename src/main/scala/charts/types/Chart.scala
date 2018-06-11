package charts.types

import vegas.DSL.ExtendedUnitSpecBuilder



/**
  * This class represent <a href ="https://vega.github.io/vega-lite/examples/">Chart object</a>
  */
abstract class Chart {

  /**
    * The method create <a href ="https://vega.github.io/vega-lite/examples/">visualization object </a>
    *
    * @param dataVisualizer The paint meta data object.
    * @return The paint object.
    */
  def create(dataVisualizer: DataVisualizer): ExtendedUnitSpecBuilder

}

