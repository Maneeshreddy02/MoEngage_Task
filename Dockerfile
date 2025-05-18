# Use a base image with Java JDK for building (from Eclipse Adoptium)
FROM eclipse-temurin:17-jdk-jammy as builder

# Set the working directory
WORKDIR /app

# Copy the Maven pom.xml and the project source code
COPY pom.xml .
COPY src ./src

# Build the WAR file using Maven
RUN apt-get update && apt-get install -y maven && \
    mvn clean package

# Use a smaller base image with Java JRE for the final stage (from Eclipse Adoptium)
FROM eclipse-temurin:17-jre-jammy

# Set the working directory
WORKDIR /app

# Copy the webapp-runner jar
RUN apt-get update && apt-get install -y curl && \
    curl -L https://repo1.maven.org/maven2/com/github/jsimone/webapp-runner/9.0.40.0/webapp-runner-9.0.40.0.jar -o webapp-runner.jar

# Copy the built WAR file from the builder stage
COPY --from=builder /app/target/moengage-task-1.0-SNAPSHOT.war /app/moengage-task.war

# Expose the port webapp-runner will listen on
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "webapp-runner.jar", "--port", "$PORT", "moengage-task.war"]