FROM openjdk:11-jre-slim

COPY ./build/libs/*.jar app.jar

HEALTHCHECK --interval=10s --timeout=3s --retries=4 CMD curl -sS 127.0.0.1 || exit 1

ENV TZ=Asia/Seoul
EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]
