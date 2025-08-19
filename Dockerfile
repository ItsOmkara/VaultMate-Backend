# Step 1: Build stage
FROM maven:3.9.8-eclipse-temurin-21 AS build

WORKDIR /app
COPY . .

# Fix permission issue
RUN chmod +x mvnw

# Run build
RUN ./mvnw clean package -DskipTests

# Step 2: Runtime stage
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 9090
ENTRYPOINT ["java", "-jar", "app.jar"]