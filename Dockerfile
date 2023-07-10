FROM gradle:8.1.1-jdk17-jammy as build

WORKDIR /app

COPY gradle gradle
COPY gradlew .
COPY build.gradle.kts .
COPY gradle.properties .
COPY settings.gradle.kts .
COPY src src

RUN --mount=type=cache,target=/root/.gradle gradle shadowJar --no-daemon
RUN cp build/libs/*-all.jar /app.jar

FROM gradle:8.1.1-jdk17-jammy as runnable
COPY --from=build /app.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]