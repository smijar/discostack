FROM jeanblanchard/java:jre-8
RUN apk add --update bash && rm -rf /var/cache/apk/*
# FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
ARG WAIT
COPY ${JAR_FILE} app.jar
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]