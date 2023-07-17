FROM openjdk:17
WORKDIR /app
COPY ./target/*.jar ./application.jar

EXPOSE 7171

ENTRYPOINT java -jar application.jar