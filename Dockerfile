FROM openjdk:11-jre-slim

COPY ./build/libs/*.jar app.jar

HEALTHCHECK --interval=10s --timeout=5s --start-period=30s --retries=2 CMD curl -f http://127.0.0.1:8080 || exit 1

ENV TZ=Asia/Seoul
EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]
