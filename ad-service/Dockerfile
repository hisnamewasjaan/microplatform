FROM eclipse-temurin:17-jdk
RUN adduser --system --group hwj
USER hwj:hwj
ARG JAR_FILE=build/libs/\*.jar
COPY ${JAR_FILE} app.jar
ENV DB_URL=jdbc:h2:~/microtest
ENV ROOT_LOGGING_LEVEL=debug
#ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "/app.jar"]
ENTRYPOINT ["java", "-jar", "/app.jar"]
