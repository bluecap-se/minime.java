#
# DOCKER
#

.PHONY: up
up:
	docker-compose -f devops/docker/docker-compose.yml up -d --build

.PHONY: down
down:
	docker-compose -f devops/docker/docker-compose.yml down

.PHONY: build
build:
	docker build -t bluecap/minime.java:latest -f devops/docker/Dockerfile-backend .

.PHONY: push
push: build
	docker push bluecap/minime.java:latest


#
# FRONTEND
#

.PHONY: frontend-install
frontend-install:
	npm --prefix frontend install

.PHONY: frontend-run
frontend-run:
	npm --prefix frontend run start

.PHONY: frontend-test
frontend-test:
	npm --prefix frontend run test

.PHONY: frontend-build
frontend-build:
	npm --prefix frontend run build


#
# INFRASTRUCTURE
#

.PHONY: infra-init
infra-init:
	terraform -chdir=devops/terraform init

.PHONY: infra-format
infra-format:
	terraform -chdir=devops/terraform fmt

.PHONY: infra-validate
infra-validate:
	terraform -chdir=devops/terraform validate

.PHONY: infra-apply
infra-apply:
	terraform -chdir=devops/terraform apply

.PHONY: infra-show
infra-show:
	terraform -chdir=devops/terraform show

.PHONY: infra-destroy
infra-destroy:
	terraform -chdir=devops/terraform destroy


#
# TEST
#

.PHONY: test
test:
	docker exec -it mini_backend test

.PHONY: test-coverage
test-coverage:
	@echo "Coming soon"
