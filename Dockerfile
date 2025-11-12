# 1. Build stage: Gradle로 JAR 생성 및 권한 설정
# gradle:9.2.0-jdk21-jammy 이미지를 사용합니다.
FROM gradle:9.2.0-jdk21-jammy AS build
WORKDIR /app
# 모든 프로젝트 파일 복사
COPY . .
# Gradle Wrapper 파일에 실행 권한을 부여하여 빌드 중 권한 오류를 방지합니다.
RUN chmod +x ./gradlew 
# Gradle Wrapper를 사용하여 'app.jar' 파일을 생성합니다.
RUN ./gradlew clean bootJar --no-daemon

# 2. Run stage: JAR 실행을 위한 가볍고 보안에 유리한 JRE 환경
# eclipse-temurin:21-jdk-jammy 대신 JRE(Java Runtime Environment)만 포함된 
# eclipse-temurin:21-jre-jammy 이미지를 사용하여 이미지를 경량화합니다.
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
# Build Stage에서 생성된 'app.jar' 파일을 정확한 이름과 경로로 복사합니다.
COPY --from=build /app/build/libs/app.jar app.jar
# 복사된 JAR 파일을 실행합니다.
ENTRYPOINT ["java","-jar","/app.jar"]