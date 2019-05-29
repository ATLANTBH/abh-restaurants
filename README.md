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

If you already have postgres be sure to run scripts located in `webapp/deployment/local/postgres/docker-entrypoint-initdb.d`!

Import project in your favorite IDE and be sure to select `develop` maven profile.

Start the web application by running `main` method in the `Application` class. If you want to run web application without importing into project, run following:

```bash
mvn clean spring-boot:run -f webapp/pom.xml -P develop 
```