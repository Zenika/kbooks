FROM openjdk:8u162-jre
COPY ./build/libs/*.war /opt/app.war
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/app.war"]
