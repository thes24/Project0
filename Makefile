# 도커 관련된 변수와 세팅입니다.
SERVER_NAME = project0-api
SERVER_VERSION = 0.1
CONTAINER_NAME = PROJECT--API
PORT = 8080

# 콘솔 색 관련 세팅입니다.
# 아래와 같이 사용하면 됩니다.
# @echo "$(GRENN)Hello World$(RESET)"
GREEN = \033[92m
RESET = \033[0m

# 명령어에 붙는 prefix입니다.
PREFIX = $(GREEN)[PROJECT0]$(RESET)

server:
	@echo "$(PREFIX) Building api server image..."
	@docker build \
		--platform linux/x86_64 \
		-t $(SERVER_NAME):$(SERVER_VERSION) .
	@echo "$(PREFIX) Done building api server image."
.PHONY: server

run:
	@echo "$(PREFIX) Running api server image..."
	@docker run \
		--platform linux/x86_64 \
		--name $(CONTAINER_NAME) \
		--rm -it -d -p $(PORT):$(PORT) \
		$(SERVER_NAME):$(SERVER_VERSION)
	@echo "$(PREFIX) Success running api server image."
.PHONY: run

run-product:
	@echo "$(PREFIX) Running api server image..."
	@docker run \
		--platform linux/x86_64 \
		--name $(CONTAINER_NAME) \
		--restart always \
		-it -d -p $(PORT):$(PORT) \
		$(SERVER_NAME):$(SERVER_VERSION)
	@echo "$(PREFIX) Success running api server image."
.PHONY: run-product

serve:
	@echo "$(PREFIX) Running api server..."
	@go run main.go \
		-env native_local
	@echo "$(PREFIX) Success running api server."
.PHONY: serve

stop:
	@echo "$(PREFIX) Stopping api server..."
	@docker stop \
		$(CONTAINER_NAME)
	@echo "$(PREFIX) Success stopping api server."
.PHONY: stop

DANGLING_IMAGE = $(shell docker images -f dangling=true -q)
API_IMAGE = $(shell docker images --filter=reference="project0-api" -q)
clean:
	@echo "$(PREFIX) Prune docker system..."
	@docker system prune -a -f
	@docker volume prune -f
	@docker image prune -a -f
	@docker container prune -f
	@docker builder prune -a -f
	@echo "$(PREFIX) Success Prune docker system."

	@echo "$(PREFIX) Remove project0-api server..."
ifneq ($(shell docker ps -aqf "name=$(CONTAINER_NAME)"),)
	@docker rm -f \
		$(shell docker ps -aqf "name=$(CONTAINER_NAME)")
endif
	@echo "$(PREFIX) Success Removing project0-api server."

	@echo "$(PREFIX) Removing dangling images..."
ifneq ($(DANGLING_IMAGE),)
	@docker rmi -f $(DANGLING_IMAGE)
endif
	@echo "$(PREFIX) Done removing dangling images."

	@echo "$(PREFIX) Removing all project0-api images..."
ifneq ($(API_IMAGE),)
	@docker rmi -f $(API_IMAGE)
endif
	@echo "$(PREFIX) Done removing all project0-api images."
.PHONY: clean