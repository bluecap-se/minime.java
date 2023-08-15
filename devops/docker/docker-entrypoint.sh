#!/bin/bash

set -eu

if [ "$1" = 'runproduction' ]; then
	exec java -jar /minime/app-1.0.0.jar
elif [ "$1" = 'runserver' ]; then
	exec mvn spring-boot:run
elif [ "$1" = 'test' ]; then
	exec SPRING_CONFIG_NAME=application-test mvn test
fi

exec $@
