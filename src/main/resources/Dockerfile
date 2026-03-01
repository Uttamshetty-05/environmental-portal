# Use OpenJDK 17 runtime base image
FROM eclipse-temurin:17-jdk-alpine

# Argument for the jar file path (adjust if your jar name is different)
ARG JAR_FILE=target/environmental_portal_backend-0.0.1-SNAPSHOT.jar

# Copy the jar file to the container filesystem
COPY ${JAR_FILE} app.jar

# Expose port 8080 (default Spring Boot port)
EXPOSE 8080

# Define the command to run your jar file
ENTRYPOINT ["java", "-jar", "/app.jar"]
