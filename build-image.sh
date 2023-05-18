#!/bin/sh
# Bash script
DOCKER_HUB_REPO=tnanhd/sampleapi
./gradlew clean build
docker build --platform=linux/amd64 -t $DOCKER_HUB_REPO .
