
# abh-restaurants webapp

## Introduction

This is ABH DevDays demo app.

## Local development

Import project in your favorite IDE, make sure correct maven profile has been selected (`develop`).

Start postgres database. Use `docker-compose` for easier integration with postgres:

```bash
cd deployment/local
docker-compose up
```

## Unit tests

`mvn clean test`

## Production build

`mvn clean package -P production`

This will build service and docker image containing the service.

`secrets.properties` file should available and mounted on `/secrets/` directory in container. 
See `example.secrets.properties` for available configuration properties.
