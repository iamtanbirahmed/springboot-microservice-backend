FROM maven:3.8.6-eclipse-temurin-11 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:11-jre
VOLUME /tmp
EXPOSE 8443
COPY --from=build /app/target/*.jar service-gateway.jar
ENTRYPOINT ["java","-jar","/service-gateway.jar"]
