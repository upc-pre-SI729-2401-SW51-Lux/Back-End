# Start with a base image containing Java runtime and Maven
FROM jelastic/maven:3.9.4-openjdk-22.ea-b17

WORKDIR /app

# Copy pom.xml and src directory to the container
COPY pom.xml .
COPY src ./src
RUN mvn  spring-boot:run -Dspring-boot.run.profiles=dev
# Package the application
RUN mvn package -DskipTests

# The application's jar file
ARG JAR_FILE=target/*.jar

# Add the application's jar to the container
COPY ${JAR_FILE} /app/app.jar


# Run the jar file
ENTRYPOINT ["java","-jar","/app/app.jar"]