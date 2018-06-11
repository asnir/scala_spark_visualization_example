#!/bin/bash -x 

: ${SOURCE_HOME_DIR=$(pwd)}
: ${DATA_DIR:=/src/main/resources/data/Crimes.csv}
: ${OUTPUT_DIR:=/target/output/}
: ${JAR:=/target/scala-2.11/visualization_example-assembly-0.1.jar}
: ${SPARK_MASTER:=local[*]}

echo $SOURCE_HOME_DIR
echo ${SOURCE_HOME_DIR}${JAR}

sbt assembly

#Clean old results 
sudo rm -rf ${SOURCE_HOME_DIR}/target/output/

#Run the program.
docker run --rm -it -p 4040:4040 \
 	-v ${SOURCE_HOME_DIR}${JAR}:/tmp/code/example.jar \
	-v ${SOURCE_HOME_DIR}${DATA_DIR}:/tmp/data/Crimes.csv \
	-v ${SOURCE_HOME_DIR}${OUTPUT_DIR}:/tmp/output \
	gettyimages/spark \
	./bin/spark-submit \
 		--class CrimeAnalyzer \
		--master local[*] /tmp/code/example.jar \
		 /tmp/data/Crimes.csv /tmp/output crime_analyzer
