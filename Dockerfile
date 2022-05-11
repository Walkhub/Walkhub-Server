FROM openjdk:11-jre-slim

COPY ./build/libs/*.jar app.jar

HEALTHCHECK --interval=5s --timeout=5s --start-period=15s --retries=10 CMD curl -f http://localhost:8080 || exit 1

ENV TZ=Asia/Seoul
EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]
