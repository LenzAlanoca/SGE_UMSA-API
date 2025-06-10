# Etapa de construcción
FROM maven:3.9.6-eclipse-temurin AS build
WORKDIR /app

# Copiar archivos fuente, ignorando archivos innecesarios
COPY pom.xml ./
RUN mvn dependency:go-offline
COPY src ./src
COPY .mvn/wrapper/ .mvn/wrapper/
COPY mvnw ./
RUN chmod +x mvnw 
RUN mvn clean package -DskipTests


# Etapa de ejecución
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copiar el archivo .jar generado
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
