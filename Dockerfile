# Etapa de construcción
FROM maven:3.9.6-eclipse-temurin-22 AS builder

# Crea directorio de trabajo
WORKDIR /app

# Copia los archivos necesarios
COPY pom.xml .
COPY src ./src

# Compila el proyecto y empaqueta el JAR (sin tests si lo deseas)
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:22-jdk

# Crea directorio de trabajo en el contenedor final
WORKDIR /app

# Copia el JAR desde el builder (ajusta el nombre si es distinto)
COPY --from=builder /app/target/casa-europa-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto que usa Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]