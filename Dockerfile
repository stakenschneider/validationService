FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
ADD validationservice-boot-docker-0.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

