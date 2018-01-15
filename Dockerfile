<<<<<<< HEAD
FROM openjdk:alpine

EXPOSE 8080
RUN apk add --no-cache git
RUN apk add --no-cache maven
RUN git clone https://github.com/stakenschneider/validationservice
WORKDIR /validationservice
RUN mvn clean install -e
CMD mvn exec:java -eCMD mvn exec:java -e
=======
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
ADD validationservice-boot-docker-0.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

>>>>>>> 9e486ee9c71894dfef0f290f64e0e9db8b41d2a0
