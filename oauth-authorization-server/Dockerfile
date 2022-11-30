FROM openjdk:17-jdk-alpine
RUN addgroup -S hwj && adduser -S hwj -G hwj
USER hwj:hwj
ARG JAR_FILE=target/oauth-authorization-server-0.1.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
