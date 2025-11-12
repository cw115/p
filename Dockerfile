# 1. Build stage: Gradle로 JAR 생성
FROM gradle:8.3.2-jdk21 AS build
WORKDIR /app
COPY . .
RUN gradle clean build --no-daemon

# 2. Run stage: JAR 실행
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
