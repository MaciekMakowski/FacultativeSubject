FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar facultative-1.0.0-VERSION.jar
ENTRYPOINT ["java","-jar","/facultative-1.0.0-VERSION.jar"]