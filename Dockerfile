# FROM eclipse-temurin:21-jre
# WORKDIR /app
# COPY target/moneymanager-0.0.1-SNAPSHOT.jar moneymanager-v1.0.jar
# EXPOSE 9090
# ENTRYPOINT ["java", "-jar", "moneymanager-v1.0.jar"]

# FROM eclipse-temurin:21-jre
# WORKDIR /app
#
# COPY target/moneymanager-0.0.1-SNAPSHOT.jar app.jar
#
# # Render dynamically sets $PORT, so use that
# ENV PORT=10000
# EXPOSE ${PORT}
#
# ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=${PORT}"]

# Step 1: Build stage
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Step 2: Runtime stage
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/moneymanager-0.0.1-SNAPSHOT.jar moneymanager-v1.0.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "moneymanager-v1.0.jar"]