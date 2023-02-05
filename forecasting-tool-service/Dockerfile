FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -S spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} forecasting-tool-service-0.0.1.jar
ENTRYPOINT ["java", "-jar". "/forecasting-tool-service-0.0.1.jar"]