APP_NAME=ad-service


# HELP
# This will output the help for each task
# thanks to https://marmelab.com/blog/2016/02/29/auto-documented-makefile.html
.PHONY: help

help: ## This help.
	@awk 'BEGIN {FS = ":.*?## "} /^[a-zA-Z_-]+:.*?## / {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}' $(MAKEFILE_LIST)

.DEFAULT_GOAL := help

# Gradle TASKS
# run tests
test: ## Run tests
	./gradlew -g ~/.gradle5 --console verbose -PtestloggerQuiet=true test

# bootRun
bootRun: ## Run spring boot app
	export DB_URL='jdbc:h2:/Users/janemilbentzen/h2/microtest'; \
	export LOGGING_LEVEL_ROOT=warn; \
	export SPRING_SECURITY_OAUTH_2_RESOURCESERVER_JWT_ISSUER_URI='http://localhost:8083/realms/microads'; \
	export SPRING_SECURITY_OAUTH_2_RESOURCESERVER_JWK_SET_URI='http://localhost:8083/realms/microads/protocol/openid-connect/certs'; \
	./gradlew -g ~/.gradle5 --console verbose bootRun

# bootRun
bootRun-postgres: ## Run spring boot app
	export POSTGRES_DB='microads'; \
	export POSTGRES_USER='micro'; \
	export POSTGRES_PASSWORD='micropwd'; \
	export DB_URL='jdbc:postgresql://db:5432/${POSTGRES_DB}'; \
	export LOGGING_LEVEL_ROOT=warn; \
	export SPRING_SECURITY_OAUTH_2_RESOURCESERVER_JWT_ISSUER_URI='http://localhost:8083/realms/microads'; \
	export SPRING_SECURITY_OAUTH_2_RESOURCESERVER_JWK_SET_URI='http://localhost:8083/realms/microads/protocol/openid-connect/certs'; \
	./gradlew -g ~/.gradle5 --console verbose bootRun

# build jar
jar: ## Build the jar file
	./gradlew -g ~/.gradle5 --console verbose -PtestloggerQuiet=true assemble

# DOCKER TASKS
# Build the container
build: jar ## Build the container
	docker build --build-arg JAR_FILE=build/libs/\*.jar --tag hwj/$(APP_NAME) .

build-nc: ## Build the container without caching
	docker build --no-cache --build-arg JAR_FILE=build/libs/\*.jar -t hwj/$(APP_NAME) .
	#docker build --no-cache -t $(APP_NAME) .

run: ## Run container on port 8081
	docker run -p 8081:8081 --name="$(APP_NAME)" hwj/$(APP_NAME)
#run: ## Run container on port configured in `config.env`
	#docker run -i -t --rm --env-file=./config.env -p=$(PORT):$(PORT) --name="$(APP_NAME)" $(APP_NAME)

up: build run ## Run container (Alias to run)

stop: ## Stop and remove a running container
	docker stop $(APP_NAME); docker rm $(APP_NAME)
