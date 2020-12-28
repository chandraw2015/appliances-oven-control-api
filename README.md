# appliances-oven-control-api

This codebase was created to demonstrate a basic function controls of an Oven.

# How it works

The application uses Spring boot (Web, Spring-Data-JPA).

* Use the idea of Domain Driven Design to separate the business term and infrastructure term.
* The Functions of Oven are Exposed using stateless REST Apis.
* Spring Data JPA is used to easily manage the database relations and transactions.

 The code organize as this:

1. `controller` is the web layer to implement by Spring Web
2. `service` is the business model including services
3. `model`  includes all business entities
4. `repository`  contains all the implementation classes of JPA.

# Database

It uses a H2 in memory database (for now), can be changed easily in the `application.properties` for any other database.

# Getting started

You need Java 8 installed and any latest version of Maven.
 * Run : mvn spring-boot:run
 * RightClick Start OvenControlApplication.java to build and start the Application.

Once Application is build and started, It will be launched on port 8080 locally.

In memory database console url :http://localhost:8080/h2-console.
ServerName, Username and Password are provided in application.properties file.

Use Collections at location src/main/java/resources/postman to run the apis through postman.




