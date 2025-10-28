# Spring-AI Simple Chat Application
=====================================

## Table of Contents
-----------------

1. [Overview](#overview)
2. [Build and Run](#build-and-run)
3. [Dependencies](#dependencies)
4. [Features](#features)
5. [Usage](#usage)

## Overview
-----------

This is a simple Spring Boot application that utilizes the power of AI to create a chatbot. It uses LLaMA, an open-source large language model developed by Meta AI.

## Build and Run
-----------------

### Prerequisites

* Java 21 (JDK)
* Ollama 3.2 LLM

### Build and Run Command

To build the application and run it locally:

```bash
mvn clean package
java -jar target/spring-ai-simple-chat-0.0.1.jar
```

## Dependencies
--------------

This project uses the following dependencies:

* Spring Boot Starter Web: for creating a web-based chat interface
* LLaMA 3.2 LLM: for generating responses to user input
* Ollama Java Client: for interacting with the LLaMA model

## Features
---------

### User Interface

The application features a simple web interface that allows users to interact with the chatbot.

### Chatbot Functionality

The chatbot uses the LLaMA model to generate responses to user input. It can engage in basic conversations, answer questions, and provide information on various topics.

## Usage
-----

1. Open a web browser and navigate to `http://localhost:8080/swagger-ui/index.html`.
2. It has only one api, which takes user prompt and wait for LLM to respond.
3. The LLM doesn't keep the context.

Note: This is a simple implementation and may not cover all scenarios. You can extend the application to add more features and functionality as needed.


## Docker
------

This project includes a `Dockerfile` so you can build and run the application as a container.

Ports
- The application listens on port 8080 by default. The container image exposes that port.

Environment variables (Spring Boot relaxed binding)
- SPRING_AI_OLLAMA_BASE_URL — overrides `spring.ai.ollama.base-url` (example default in `application.yml`: `http://host.docker.internal:11434`).
- SERVER_PORT — overrides the server port (spring property `server.port`).
- You can also pass any other Spring Boot properties as environment variables using the standard Spring Boot relaxed binding rules.

Build the Docker image

```bash
docker build -t spring-ai-simple-chat:latest .
```

Run the container (Ollama running on your host / Docker Desktop on macOS)

```bash
# If Ollama is running on the host (macOS Docker Desktop), use host.docker.internal
docker run --rm -p 8080:8080 \
  -e SPRING_AI_OLLAMA_BASE_URL=http://host.docker.internal:11434 \
  spring-ai-simple-chat:latest
```

Run the container (Ollama running in another container on the same Docker network)

```bash
# Create a network and run Ollama (example) with name 'ollama'
# docker network create ai-net
# docker run -d --name ollama --network ai-net <ollama-image>

docker run --rm -p 8080:8080 \
  --network ai-net \
  -e SPRING_AI_OLLAMA_BASE_URL=http://ollama:11434 \
  spring-ai-simple-chat:latest
```

Alternative: run the packaged JAR inside an OpenJDK container (no Dockerfile)

```bash
docker run --rm -p 8080:8080 -v "$(pwd)/target/spring-ai-simple-chat-0.0.1.jar:/app/app.jar" \
  openjdk:21-jdk java -jar /app/app.jar
```

Troubleshooting
- On macOS Docker Desktop, `host.docker.internal` resolves to the host so the app container can reach an Ollama instance running on the host.
- On Linux, `host.docker.internal` may not be available; use a user-defined network and reference the Ollama container by name, or set the host machine IP.
- If the app cannot reach the LLM, check that the base URL is reachable from inside the container (e.g., `docker exec` + curl) and that Ollama is listening on port 11434.

Where to go next
- After the container is running visit `http://localhost:8080/swagger-ui/index.html` to test the API.