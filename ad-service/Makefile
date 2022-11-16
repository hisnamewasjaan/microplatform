APP_NAME=ad-service


# HELP
# This will output the help for each task
# thanks to https://marmelab.com/blog/2016/02/29/auto-documented-makefile.html
.PHONY: help

help: ## This help.
	@awk 'BEGIN {FS = ":.*?## "} /^[a-zA-Z_-]+:.*?## / {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}' $(MAKEFILE_LIST)

.DEFAULT_GOAL := help

# Gradle TASKS
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
