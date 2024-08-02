FROM maven:3.9.8-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21
COPY --from=build /target/portal-0.0.1-SNAPSHOT.jar portal.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "portal.jar"]