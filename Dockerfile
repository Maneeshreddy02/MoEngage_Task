# Use official Tomcat base image
FROM tomcat:10.1-jdk17

# Remove default webapps (optional, for cleanliness)
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy your WAR file into Tomcat's webapps directory
COPY Response-code-manager.war /usr/local/tomcat/webapps/ROOT.war

# Expose port 8080
EXPOSE 8080

# Start Tomcat server
CMD ["catalina.sh", "run"]
