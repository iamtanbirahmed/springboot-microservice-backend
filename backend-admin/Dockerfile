FROM maven:3.8.6-eclipse-temurin-11 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:11-jre
VOLUME /tmp
EXPOSE 1001
COPY --from=build /app/target/*.jar admin-service.jar
ENTRYPOINT ["java","-jar","/admin-service.jar"]