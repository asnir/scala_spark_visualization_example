#!/bin/bash -x 

: ${SOURCE_HOME_DIR=$(pwd)}
: ${OUTPUT_DIR:=/target/output/}

#Build jar & docker-image
sbt clean assembly
docker build -t spark_visualization .

#Clean old results 
sudo rm -rf ${SOURCE_HOME_DIR}/target/output/

#Run container
docker run --rm -it -p 4040:4040 -v ${SOURCE_HOME_DIR}${OUTPUT_DIR}:/tmp/output spark_visualization

