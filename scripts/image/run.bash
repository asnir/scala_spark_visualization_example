#!/bin/bash -x 

wget -O /tmp/data/Crimes.csv https://athena-dev-task.s3-eu-west-1.amazonaws.com/Crimes.csv 
./bin/spark-submit \
	--class CrimeAnalyzer --master local[*] /tmp/code/example.jar /tmp/data/Crimes.csv /tmp/output crime_analyzer

