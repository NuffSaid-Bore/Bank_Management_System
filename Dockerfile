# Use an official Java runtime as a parent image
FROM openjdk:17-jdk-slim
 
# Set the working directory in the container
WORKDIR /app
 
# Copy the Maven build file and source code
COPY . /app
 
# Install Maven
RUN apt-get update && apt-get install -y maven
 
# Build the application with Maven
RUN mvn clean package
 
# Run the application
ENTRYPOINT ["java", "-jar", "target/Bank_Management_System-0.0.1-SNAPSHOT.jar"]
 
# Expose the port the app runs on
EXPOSE 8000
