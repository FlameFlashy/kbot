APP=$(shell basename $(shell git remote get-url origin))
#REGISTRY=ghcr.io/flameflashy
REGISTRY ?= flameflashy
VERSION=$(shell git describe --tags --abbrev=0)$(shell git rev-parse --short HEAD)
TARGETOS ?=linux
TARGETARCH ?=arm64 

format:
	gofmt -s -w ./

lint:
	golint

test:
	go test -v

get:
	go get

build: format get
	CGO_ENABLED=0 GOOS=${TARGETOS} GOARCH=${TARGETARCH} go build -v -o kbot -ldflags "-X="github.com/flameflashy/kbot/cmd.appVersion=${VERSION}

image:
	docker build . -t ${REGISTRY}/${APP}:${VERSION}-${TARGETOS}-${TARGETOSARCH} --build-arg TARGETOS=${TARGETOS} --build-arg TARGETOSARCH=${TARGETOSARCH} --no-cache

push: 
	docker push ${REGISTRY}/${APP}:${VERSION}-${TARGETOS}-${TARGETARCH}


clean:
	rm -rf kbot
	docker rmi ${REGISTRY}/${APP}:${VERSION}-${TARGETOS}-${TARGETARCH}

linux: # Build for linucx, by default this made for arm64
	${MAKE} build TARGETOS=linux

windows: # Build for windows, by default this made for arm64
	${MAKE} build TARGETOS=windows
	
macos: # Build for macos, by default this made for arm64
	${MAKE} build TARGETOS=darwin