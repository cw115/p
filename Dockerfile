# 1. Build stage: Gradle로 JAR 생성 및 권한 설정
FROM gradle:9.2.0-jdk21-jammy AS build
WORKDIR /app
COPY . .
RUN chmod +x ./gradlew 
RUN ./gradlew clean bootJar --no-daemon

# 2. Run stage: JAR 실행을 위한 JRE 환경
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/build/libs/app.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]