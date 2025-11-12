# 1. Build stage: Gradle로 JAR 생성 및 권한 설정
# gradle:9.2.0-jdk21-jammy는 빌드에 필요한 모든 것을 제공합니다.
FROM gradle:9.2.0-jdk21-jammy AS build
WORKDIR /app
# 모든 프로젝트 파일을 컨테이너로 복사
COPY . .
# Gradle Wrapper 파일에 실행 권한을 부여합니다. (권한 문제 해결)
RUN chmod +x ./gradlew 
# Gradle Wrapper를 사용하여 JAR 파일 (app.jar)을 생성합니다.
RUN ./gradlew clean bootJar --no-daemon

# 2. Run stage: JAR 실행을 위한 가볍고 보안에 유리한 JRE 환경
# eclipse-temurin:21-jre-jammy는 실행에 필요한 JRE만 포함하여 이미지가 가볍습니다.
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
# Build Stage에서 생성된 'app.jar' 파일을 정확한 이름과 경로로 복사합니다.
COPY --from=build /app/build/libs/app.jar app.jar
# 복사된 JAR 파일을 실행합니다.
ENTRYPOINT ["java","-jar","/app.jar"]
