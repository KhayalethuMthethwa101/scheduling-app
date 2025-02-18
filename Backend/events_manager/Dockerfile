FROM maven:3.9.9-eclipse-temurin-23 AS build
<<<<<<< HEAD
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY scr ./src
RUN mvn clean package -DskipTests
=======
COPY . .
RUN mvn clean install
>>>>>>> f4db9dd2a628b7686fb6fac6605c524794e4b296

FROM openjdk:23-jdk-slim
WORKDIR /app
COPY --from=build /app/target/events_manager-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "events_manager-0.0.1-SNAPSHOT.jar" ]
