# Use Eclipse Temurin JDK 23 as the base image
FROM eclipse-temurin:23-jdk

# Set the working directory
WORKDIR /app

# Copy the Maven files
COPY pom.xml .
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose the port the app runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "target/DetskaApp-1.0-SNAPSHOT.jar"] 