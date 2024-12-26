# Copile the project
FROM openjdk:17-jdk as build
WORKDIR /app
COPY . .
RUN ./mvnw clean package

# Run the project
FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY --from=build /app/target/k8s-0.0.1.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]