# Use official Maven image to build the application
FROM maven:3.9-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Use a minimal Java runtime for the application
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/buddy-storage-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]