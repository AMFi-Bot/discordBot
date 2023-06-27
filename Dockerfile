FROM eclipse-temurin:17-jdk-alpine as build

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN --mount=type=cache,target=/root/.m2 ./mvnw package -DskipTests
RUN cp target/*.jar /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]