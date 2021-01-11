# oven-control-api

This codebase was created to demonstrate basic function controls of an Oven.

# How it works

The application uses Spring boot (Web, Spring-Data-JPA).

- Use the idea of Domain Driven Design to separate the business term and infrastructure term.
- The Functions of Oven are Exposed using stateless REST Apis.
- Spring Data JPA is used to easily manage the database relations and transactions.

The code organize as this:

1. `controller` is the web layer to implement by Spring Web
2. `service` is the business model including services
3. `model` includes all business entities
4. `repository` contains all the implementation classes of JPA.

# Database

It uses a H2 in memory database (for now), can be changed easily in the `application.properties` for any other database.

# Getting started

You need Java 8 installed and any latest version of Maven.

- Build using : mvn clean install
- Run Application locally : mvn spring-boot:run
  Once Application is build and started, It will be launched on port 8080 locally.

In memory database console url :http://localhost:8080/h2-console.
ServerName, Username and Password are provided in application.properties file.

- Use Collections at location src/main/java/resources/postman to run the apis through postman.
  OR

* Run Below Curl Commands

You need to onboard an Oven using below curl 1 for testing all endpoints

1.  To Onboard an oven: curl --location --request POST 'http://localhost:8080/api/oven/onboard' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "name":"BestOven",
    "model":"T-7658",
    "version":"TE-9289"
}'

2.  To get all Oven : curl --location --request GET 'http://localhost:8080/api/ovens'

3.  To get Oven With Oven Id : curl --location --request GET 'http://localhost:8080/api/oven/1'

4.  To set an Oven Program : curl --location --request PUT 'http://localhost:8080/api/oven/1/program' \
     --header 'Content-Type: application/json' \
     --data-raw '{
    "ovenState": "STARTED",
    "temperature": 500
    }'

# Swagger-UI Api Documentation

Swagger UI can be accessed after running application locally on http://localhost:8080/swagger-ui/index.html
 
# How an Oven can be programmed

1. Lifecycle of an oven STOPPED->STARTED->COOKING->IDLE
2. Oven can not directly be set to COOKING or IDLE state you need to START it otherwise it will throw OvenNotStarted Exception.
3. Oven can be programmed to STOPPED state at any point of time.
4. If Oven is in IDLE State it can be set to COOKING or STOPPED state.
