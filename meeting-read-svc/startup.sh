#!/bin/bash

mvn clean install -DskipTests=true

docker build . -t meeting-read-svc:latest
