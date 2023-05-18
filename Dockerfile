FROM amazoncorretto:11.0.17-alpine

ARG HOME=/sampleapi

WORKDIR $HOME

ARG APP_NAME=product-sample-api
ARG APP_VERSION=0.0.1-SNAPSHOT
ARG JAR_FILE=build/libs/$APP_NAME-$APP_VERSION.jar
COPY $JAR_FILE app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
