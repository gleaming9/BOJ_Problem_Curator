FROM eclipse-temurin:21-jdk
LABEL maintainer=gleam9
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]