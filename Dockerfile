FROM openjdk:alpine

EXPOSE 8080
RUN apk add --no-cache git
RUN apk add --no-cache maven
RUN git clone https://github.com/stakenschneider/validationservice
WORKDIR /validationservice
RUN mvn clean install -e
CMD mvn exec:java -e