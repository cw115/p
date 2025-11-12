# Run stage: 이미 로컬에서 빌드된 JAR 실행
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

# 로컬에서 빌드된 JAR 복사
COPY build/libs/app.jar app.jar

# JAR 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]
