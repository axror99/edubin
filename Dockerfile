FROM openjdk:17-alpine
ADD target/edubin-0.0.1-SNAPSHOT.jar edubin.jar
ENTRYPOINT ["java","-jar","edubin.jar"]
EXPOSE 8080