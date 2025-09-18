# ---- Stage 1: Build ----
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

COPY . /app

RUN mvn clean install -DskipTests

# ---- Stage 2: Run ----
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/student-mgmt-0.0.1-SNAPSHOT.jar /app/student-mgmt.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "student-mgmt.jar"]