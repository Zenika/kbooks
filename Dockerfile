FROM openjdk:8u162-jdk AS builder

WORKDIR /usr/src/kbooks

COPY gradlew .
COPY gradle gradle
RUN ./gradlew wrapper

COPY . .

RUN ./gradlew build

FROM openjdk:8u162-jre
COPY --from=builder /usr/src/kbooks/build/libs/*.war /opt/app.war
ENV JDBC_DRIVER org.h2.Driver
ENV JDBC_URL jdbc:h2:mem:db;DB_CLOSE_DELAY=-1
ENV JDBC_USER sa
ENV JDBC_PASSWORD sa
EXPOSE 8080
ENTRYPOINT sleep 10 && java -Djava.security.egd=file:/dev/./urandom -jar /opt/app.war
