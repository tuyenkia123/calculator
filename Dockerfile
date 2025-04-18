FROM maven:3.8.6-amazoncorretto-17 AS builder
WORKDIR /calculator
COPY pom.xml .
COPY src ./src
RUN mvn clean install -DskipTests

FROM maven:3.8.6-amazoncorretto-17
WORKDIR /calculator
COPY --from=builder /calculator/target/*.jar calculator-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/calculator-0.0.1-SNAPSHOT.jar"]