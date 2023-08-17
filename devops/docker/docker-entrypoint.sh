#!/bin/bash

set -eu

if [ "$1" = 'runproduction' ]; then
    /minime/mvnw clean package
	exec java -jar /minime/target/app-0.0.1.jar
elif [ "$1" = 'runserver' ]; then
    /minime/mvnw clean install
	exec /minime/mvnw spring-boot:run
elif [ "$1" = 'test' ]; then
	exec SPRING_CONFIG_NAME=application-test /minime/mvnw test
fi

exec $@
