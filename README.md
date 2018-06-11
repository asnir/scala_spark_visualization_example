# Scala, Spark, Visualization example

This example analyze [crime data](https://athena-dev-task.s3-eu-west-1.amazonaws.com/Crimes.csv "crime data"). The goal is to understand which districts of this city are more prone to which crime.

The example load data and manipulate it using spark. The visualization is being done by [vegas](https://github.com/vegas-viz/Vegas "vegas").

Vegas aims to be the missing MatPlotLib for the Scala and Spark world. Vegas wraps around Vega-Lite but provides syntax more familiar (and type checked) for use within Scala.

The output of this example are:
* csv file
* html visualization file.

## Requerments:

**Use for the building & code executing:**

- jdk 1.8
- Scala 2.11.8
- sbt 0.13.8

## Instructions:
### Option 1:
run the following command:
```
./scripts/run_container.bash
```

### Option 2:

#### Step 1:
Download [this csv file](https://athena-dev-task.s3-eu-west-1.amazonaws.com/Crimes.csv "this csv file").

#### Step 2:

locate the uber jar & csv file/s in accessibly location for the spark job.
 e.g. shared storage (s3, hdfs, NFS)/ on each server in the cluster.


Run the following command (Review and set the script parameters.):

```
./scripts/run.bash
```


if you are running it via IDE at local mode add the following JVM parameter:
`-Dspark.master=local[*]`
And add the following args: [input file/dir] [output_dir] [app_name]
e.g. ./src/main/resources/data/Crimes.csv ./target/output/ crime_analyzer



------------


*You can find more visualization example [here](https://vega.github.io/vega-lite/examples/ "here")*


