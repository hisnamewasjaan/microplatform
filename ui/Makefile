APP_NAME=ui


# HELP
# This will output the help for each task
# thanks to https://marmelab.com/blog/2016/02/29/auto-documented-makefile.html
.PHONY: help

help: ## This help.
	@awk 'BEGIN {FS = ":.*?## "} /^[a-zA-Z_-]+:.*?## / {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}' $(MAKEFILE_LIST)

.DEFAULT_GOAL := help



# npm TASKS
# Install dependencies via `npm install`
install: ## Install dependencies via `npm install`
	npm install


# Angular cli TASKS
# Run `ng serve` dev server
serve: ## Run `ng serve` dev server
	ng serve

# Run `ng build` to build the project
build: ## Run `ng build` to build the project
	ng build

# Run `ng build --configuration=production` to build the project
build-prod: ## Run `ng build --configuration=production` to build the project
	ng build --configuration=production

# Run `ng test` to execute the unit tests
test: ## Run `ng test` to execute the unit tests
	ng test

# Run `ng e2e` to execute the end-to-end test
e2e: ## Run `ng e2e` to execute the end-to-end test
	ng test

# DOCKER TASKS
# Build the container
container: ## Build the container
	docker build --tag hwj/$(APP_NAME) .

up: container ## Run container (Alias to run)
	docker run -p 4200:4200 --name="$(APP_NAME)" hwj/$(APP_NAME)
