FROM eclipse-temurin:19-alpine
LABEL maintainer="ephraimamezian@gmail.com"
LABEL version="0.1.0"
ARG JAR_FILE=target/*.jar
EXPOSE 8080
COPY ${JAR_FILE} app.jar
RUN addgroup -S roadtrip_g && adduser -S roadtrip_u -G roadtrip_g
USER roadtrip_u
ENTRYPOINT ["java","-jar","/app.jar"]
