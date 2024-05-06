FROM amazoncorretto:21

EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/apiGateway-*.jar

# Add the application's jar to the container
ADD ${JAR_FILE} apiGateway.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/apiGateway.jar"]