# Etapa de build (Maven + Java 21)
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copia pom primeiro (cache das dependências)
COPY pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline

# Copia o código
COPY src ./src

# Gera o jar
RUN mvn -q -e -DskipTests package

# Etapa final: só o JAR + JRE leve
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copia o jar gerado
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
