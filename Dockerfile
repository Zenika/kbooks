FROM openjdk:8u162-jre
COPY ./build/libs/*.war /opt/app.war
ENV JDBC_DRIVER org.h2.Driver
ENV JDBC_URL jdbc:h2:mem:db;DB_CLOSE_DELAY=-1
ENV JDBC_USER sa
ENV JDBC_PASSWORD sa
EXPOSE 8080
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/opt/app.war"]
