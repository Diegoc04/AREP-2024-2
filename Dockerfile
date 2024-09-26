# Usa una imagen base con Java
FROM openjdk:11-jre-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR de tu proyecto al contenedor
COPY target/demo-0.0.1-SNAPSHOT.jar /app/demo-0.0.1-SNAPSHOT.jar

# Expone el puerto en el que tu aplicación escucha
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]
