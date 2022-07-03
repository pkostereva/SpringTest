# SpringTest

тестовое задание

## Getting started

По умолчанию, приложение занимает 8080 порт, перед запуском необходимо развернуть Postgres в Docker-е с помощью команды:

```
docker run --name postres -d -p 2020:5432 -e POSTGRES_PASSWORD=root postgres

```

Приложила коллекцию запросов Postman для удобства тестирования. Они находятся в корневой папке, файл SpringTest.postman_collection

## Fails

К сожалению, не получилось собрать работающий image приложения и составить docker-compose файл. :(