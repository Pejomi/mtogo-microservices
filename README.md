# mtogo-microservices

by Jonas, Peter & Mie

## Introduction

This is the backend code for the MTOGO-application project created by the group [Pejomi](https://github.com/Pejomi).

Also see:

* [mtogo-frontend](https://github.com/Pejomi/mtogo-frontend.git)
* [mtogo-frontend-test](https://github.com/Pejomi/mtogo-frontend-test.git)

### Domain model

![Alt text](<.images/Domain_model.jpg>)

### Hexagonal architecture

![Alt text](<.images/Hexagon_architecture.jpg>)

## Description

The project is still a work in progress. It's made for 3 different courses at CPH Business Academy for Software
developer 1. semester:

* Sofware Testing (Done)
* System Integration (Due January 2024)
* Development of large Systems (Due January 2024)

## Endpoints

1. **AuthController:**
    - `POST /login` (Method: `login`)
    - `POST /register/consumer` (Method: `registerConsumer`)
    - `POST /register/restaurant` (Method: `registerRestaurant`)

2. **MenuController:**
    - `GET /{id}` (Method: `getMenuById`)
    - `GET /restaurant/{id}` (Method: `getMenuByRestaurantId`)
    - `POST /` (Method: `createMenu`)

3. **OrderController:**
    - `POST /` (Method: `createOrder`)

4. **RestaurantController:**
    - `GET /{id}` (Method: `getRestaurantById`)
    - `GET /zip/{zipCode}` (Method: `getRestaurantsByZipCode`)
    - `GET /city/{city}` (Method: `getRestaurantsByCity`)

## Still to be implemented

* Kubernetes deployment

## How to run the project

### Kafka and Zookeeper

Run `docker-compose up` in the root folder of the project. Which will set up a Kafka and a Zookeeper cluster.

### MySQL

Make sure you have a local MySQL database running on port 3306 with the following credentials:

* Username: root
* Password: root

### Install

Run `mvn clean install` in the root folder of the project. Which will build the project and run the tests.
The databases will be created automatically by the application when it starts up.

## Then run the following commands in the root folder of the project:

### Running failsafe integration tests

`mvn failsafe:integration-test failsafe:verify`

### build surefire report

`mvn surefire-report:report`

## See all the reports

In the target foldr of the project, you will find a folder called `site`. In that folder you will find a file
called `index.html`.

## Jacoco code coverage

After running the integration tests, a jacoco code coverage report will be generated in the target folder of each
module.
But we also gathered the reports in module called `jacoco-report` which can be found in the root folder of the project.
In the root pom.xml file, we have defined a percentage of 40% code coverage for the project. Which is currently being
met.

# Prometheus and Grafana

Promeheus and Grafana is set up in the docker-compose file. Make sure you have added microservices to the
docker-compose file if you want to monitor them.

Make sure the microservices has the following dependency in the pom.xml file:

```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>io.micrometer</groupId>
            <artifactId>micrometer-registry-prometheus</artifactId>
            <scope>runtime</scope>
        </dependency>
```

And the following properties in the application.properties file:

```
management.endpoints.web.exposure.include=health,info,metrics,prometheus
management.endpoint.health.show-details=always
```

Goto `localhost:9090` in your browser to access Prometheus. You should be able to see the metrics from the
microservices.

### To access Grafana, go to `localhost:3000` in your browser.

* Username: admin
* Password: password

(Can be changed in the docker-compose file)

### Add the datasource to Grafana

![Alt text](<.images/grafana_datasource.PNG>)

* Choose Prometheus as the datasource and add the following URL: `http://prometheus:9090`
* Click on `Save & Test`

### Import the dashboard

* Click on the `+` icon in the left sidebar and choose `Import`
* Goto `Grafana_Dashboard.json` file in the root folder of the project and copy the content of the file.
* Paste the content into the `Import via panel json` field and click on `Load`
* Choose the Prometheus datasource and click on `Import`

__The dashboard should now be imported and you should be able to see the metrics.__

![Alt text](<.images/grafana_dashboard.PNG>)

## OpenApi/Swagger with Spring Security

The browser might ask for credentials when accessing the swagger-ui.html page. Spring Security is enabled by default
with credentials:

* Username: user
* Password: the password is printed in the console at startup (standard output)

Like this:
`Using generated security password: ce923da1-e0a7-449f-8e03-d9e74c0f1461` (the password is different every time)

### JIB image-building command:

mvn -pl auth-service,order-service jib:dockerBuild

### kubectl commands:

* minikube start
* minikube dashboard
* kubectl apply -f <yaml-file>
* kubectl delete -f <yaml-file>
* kubectl get pods
* kubectl describe pod <pod-name>

