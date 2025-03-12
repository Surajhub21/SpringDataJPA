FROM maven:3.9.5-openjdk-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-slim
COPY --from=build /target/SpringDataJps-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]