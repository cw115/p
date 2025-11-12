# 1. Build stage: Gradle로 JAR 생성
FROM gradle:9.2.0-jdk21-jammy AS build
WORKDIR /app
COPY . .
RUN gradle clean bootJar --no-daemon

# 2. Run stage: JAR 실행
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
<<<<<<< HEAD
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
=======
COPY --from=build /app/build/libs/app.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
>>>>>>> 6f379380484fe92e162739caed48a31faa030e7e
