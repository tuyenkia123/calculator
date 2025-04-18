FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /calculator
COPY src ./src
COPY pom.xml .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /calculator
COPY --from=build /calculator/target/*.jar calculator-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "calculator-0.0.1-SNAPSHOT.jar"]