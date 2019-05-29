# abh-restaurants

## Introduction

This is ABH DevDays demo app.

## Local development setup

Make sure you have following installed:

- Docker
- Maven3
- Java JDK 1.8

Start postgres database via docker/docker-compose:

```bash
cd webapp/deployment/local
docker-compose up
```

Import project in your favorite IDE.

Start the web application by running `main` method in the `Application` class.

If you want to run web application without importing into project, run following:

```bash
mvn clean spring-boot:run -f webapp/pom.xml -P develop 
```