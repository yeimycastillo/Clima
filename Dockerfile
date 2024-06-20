# Etapa de construcción
FROM maven:3.8.4-openjdk-17 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests

# Etapa de producción
FROM openjdk:17-jdk-slim
COPY --from=build /usr/src/app/target/weather-app-0.0.1-SNAPSHOT.jar weather-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "weather-app.jar"]
