FROM gettyimages/spark
RUN apt-get update && apt-get install -y \
    wget
ADD target/scala-2.11/visualization_example-assembly-0.1.jar /tmp/code/example.jar
ADD scripts/image/run.bash /tmp/scripts/run.bash
RUN mkdir -p /tmp/data/
ENTRYPOINT ["/bin/bash"]
CMD ["/tmp/scripts/run.bash"]
