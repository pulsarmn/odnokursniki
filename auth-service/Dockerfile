# Build Stage
FROM eclipse-temurin:21-jdk AS build

WORKDIR /build
COPY . .
RUN ./gradlew clean bootJar -x test --no-daemon && cp ./build/libs/*.jar application.jar

# Execution Stage
FROM eclipse-temurin:21-jre-jammy AS run

WORKDIR /app
COPY --from=build /build/application.jar ./application.jar

EXPOSE 8000
ENTRYPOINT ["java", "-jar", "application.jar"]
CMD ["--spring.profiles.active=prod"]
