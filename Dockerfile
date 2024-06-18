# Usa una imagen base de OpenJDK
FROM openjdk:17-jdk-slim

# Añade un argumento para la versión de la aplicación
ARG JAR_FILE=target/*.jar

# Copia el archivo JAR a la imagen
COPY ${JAR_FILE} app.jar

# Expone el puerto en el que la aplicación correrá
EXPOSE 8080

# Define el comando para correr la aplicación
ENTRYPOINT ["java","-jar","/app.jar"]
