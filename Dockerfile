FROM bellsoft/liberica-openjdk-alpine
WORKDIR /opt/app
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
COPY configuration.yaml configuration.yaml
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -jar app.jar server configuration.yaml"]