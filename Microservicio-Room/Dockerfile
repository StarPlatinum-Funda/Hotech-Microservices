FROM amazoncorretto:22

# Set the working directory in the image
WORKDIR /app

# Copy the jar file from the build stage
COPY target/Hotech-backend-0.0.1-SNAPSHOT.jar app.jar

# Expose the application's port
EXPOSE 8005

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]