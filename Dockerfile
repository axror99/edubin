FROM openjdk:17-alpine
ADD target/edubin-0.0.1-SNAPSHOT.jar myedubin.jar
ENTRYPOINT ["java","-jar","myedubin.jar"]
EXPOSE 8080