# Étape build
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -DskipTests package

# Étape runtime (image légère sans outils de build)
FROM eclipse-temurin:17-jdk
ENV JAVA_OPTS=""
WORKDIR /app
# Copie du JAR généré (adapter le nom si besoin)
COPY --from=build /app/target/*.jar /app/app.jar
# Utilisateur non-root
RUN useradd -r -s /sbin/nologin appuser && chown -R appuser:appuser /app
USER appuser
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
