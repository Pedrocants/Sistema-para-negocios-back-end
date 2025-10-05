# Etapa 1: Compilación
FROM eclipse-temurin:17-jdk-ubi10-minimal AS builder

WORKDIR /app

# Copiamos el wrapper y el POM primero (para aprovechar la cache de dependencias)
COPY ./pom.xml .
COPY ./.mvn ./.mvn
COPY ./mvnw .

# Descargamos dependencias sin compilar (acelera builds posteriores)
RUN ./mvnw dependency:go-offline

# Copiamos el código fuente
COPY ./src ./src

# Compilamos el proyecto (sin tests para acelerar)
RUN ./mvnw clean package -DskipTests

# Etapa 2: Imagen final
FROM eclipse-temurin:17-jre-ubi10-minimal

WORKDIR /app

# Exponemos el puerto
EXPOSE 8080

# Copiamos solo el JAR generado desde la etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Comando de ejecución
ENTRYPOINT ["java", "-jar", "app.jar"]