
FROM gradle:9.2.0-jdk21-jammy AS build
WORKDIR /app
COPY . .
RUN gradle clean bootJar --no-daemon


FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

COPY --from=build /app/build/libs/app.jar app.jar 
ENTRYPOINT ["java","-jar","/app.jar"]
