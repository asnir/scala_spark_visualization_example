package charts.types

import org.apache.spark.sql.DataFrame

/**
  * The is a bean class for <a href ="https://vega.github.io/vega-lite/examples/"> Chart </a>.
  * It will use Chart to create a chart & show/ save it.
  *
  * @param df          Spark data frame.
  * @param title       The chart's title.
  * @param category    The field's name, represented the aggregated field.
  * @param subCategory The field name represented the sub aggregated field (sub category).
  * @param value       The field's name representing the value of field <code>subCategory</code>.
  */
case class DataVisualizer(df: DataFrame, title: String, category: String,
                          subCategory: String, value: String)