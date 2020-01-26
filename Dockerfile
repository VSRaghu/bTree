FROM openjdk:8
EXPOSE 9001
ADD target/btree.jar btree.jar
ENTRYPOINT ["java","-jar","/btree.jar"]