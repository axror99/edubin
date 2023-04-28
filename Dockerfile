FROM openjdk:17-alpine
ADD target/edubin.jar edubin.jar
ENTRYPOINT ["java","-jar","edubin.jar"]
EXPOSE 8080