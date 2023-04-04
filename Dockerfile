# syntax=docker/dockerfile:1

# We use a multi-stage build setup.
# (https://docs.docker.com/build/building/multi-stage/)

# Stage 1 (to create a "build" image, ~360MB)
FROM eclipse-temurin:17-jdk-alpine AS builder
# smoke test to verify if java is available
RUN java -version

COPY . /usr/src/myapp/
WORKDIR /usr/src/myapp/
RUN javac JavaEatMemory.java

# Stage 2 (to create a downsized "container executable", ~180MB)
FROM eclipse-temurin:17-jre-alpine
ENV JAVA_OPTS=""
RUN apk --no-cache add ca-certificates
WORKDIR /root/
COPY --from=builder /usr/src/myapp/JavaEatMemory.class .

ENTRYPOINT sh -c "java $JAVA_OPTS JavaEatMemory"
