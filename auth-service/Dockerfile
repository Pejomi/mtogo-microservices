# Use an official OpenJDK runtime with Java 17 as a parent image
FROM openjdk:17-jdk-slim-buster

# Set the working directory to /app
WORKDIR /app

# Copy any JAR file in the target directory into the container at /app
COPY target/*.jar /app/auth-service.jar

# Specify the command to run on container startup
CMD ["java", "-jar", "auth-service.jar"]