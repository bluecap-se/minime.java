.PHONY: run down build push

run:
	docker-compose -f devops/docker/docker-compose.yml up -d --build

down:
	docker-compose -f devops/docker/docker-compose.yml down

build:
	docker build -t bluecap/minime.java:latest -f devops/docker/Dockerfile-production .

push: build
	docker push bluecap/minime.java:latest

test:
	docker run bluecap/minime.java:latest test
