FROM maven:3.9.9-eclipse-temurin-23 AS build
COPY . .
RUN mvn clean install -DskipTests

FROM openjdk:23-jdk-slim
COPY --from=build /target/events_manager-0.0.1-SNAPSHOT.jar events_manager.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "events_manager.jar" ]