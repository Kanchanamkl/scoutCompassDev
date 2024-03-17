FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/scoutcompass-0.0.1.jar scoutcompass-0.0.1.jar
EXPOSE 8081
CMD ["java","-jar","scoutcompass-0.0.1.jar"]