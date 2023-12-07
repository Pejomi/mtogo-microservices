"# mtogo-microservices" 

### Running failsafe integration tests
`mvn failsafe:integration-test failsafe:verify`

### build surefire report
`mvn surefire-report:report`

### More about security
https://youtube.com/playlist?list=PL82C6-O4XrHe3sDCodw31GjXbwRdCyyuY&si=dPz_2gSaNvHyKx17

## OpenApi/Swagger with Spring Security

The browser might ask for credentials when accessing the swagger-ui.html page. Spring Security is enabled by default with credentials:
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

