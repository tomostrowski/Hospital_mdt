FROM openjdk:8u191-jdk-alpine3.9
ADD target/noauthorization-0.0.1-SNAPSHOT.jar  .
EXPOSE 8000
CMD java -jar noauthorization-0.0.1-SNAPSHOT.jar