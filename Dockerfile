FROM eclipse-temurin:21-jdk-jammy AS builder

ARG MODULE_NAME
WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY . .

RUN ./mvnw package -pl ${MODULE_NAME} -am -DskipTests

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

ARG MODULE_NAME

COPY --from=builder /app/${MODULE_NAME}/target/*.jar application.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "application.jar"]