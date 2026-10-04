FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn package -DskipTests -B

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/target/expensetracker-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
# Render and Railway route to $PORT. Spring Boot's relaxed binding does not map
# PORT to server.port -- it only reads SERVER_PORT -- so the exec-form ENTRYPOINT
# below would leave the app on 8080 while the platform expects a different port,
# producing a 502. Shell form expands $PORT at runtime instead.
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT:-8080}"]
