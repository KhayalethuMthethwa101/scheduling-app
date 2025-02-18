FROM maven:3.9.9-eclipse-temurin-23 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY scr ./src
RUN mvn clean package -DskipTests

FROM openjdk:23-jdk-slim
WORKDIR /app
COPY --from=build /app/target/events_manager-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "events_manager-0.0.1-SNAPSHOT.jar" ]
