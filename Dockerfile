FROM openjdk:11-jre-slim
VOLUME /tmp
ADD /build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar", "-Dspring.profiles.active=production", "/app.jar"]