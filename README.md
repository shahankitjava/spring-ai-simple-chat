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
java -jar target/spring-ai-simple-chat.jar
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