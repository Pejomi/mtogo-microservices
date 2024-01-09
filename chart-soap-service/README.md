# Chart SOAP Service

## Introduction
This is a custom made SOAP service for the MTOGO-application project created by the group Pejomi.

## Description
The Chart SOAP Service is a SOAP service that is used to generate charts for the MTOGO-application project. It can generate a **bar-chart**, **dougnut-chart** and a **line-chart**.

## Example of using the SOAPUI to test the service:
By using SOAPUI we can test the service by sending a request to the service and see if we get the expected response back.

### Create a new SOAP project:
In the SOAPUI application we can create a new project and then add a new SOAP definition to the project. 
Then we can add the WSDL file from the service (http://localhost:8089/ws/charts.wsdl) to the SOAP definition and then we can see the different methods that the service has.
![Alt text](<../.images/soap ui new project.png>)

### Requesting a bar-chart
We can test the service by sending a request to the service and see if we get the expected response back.

The request for a bar-chart looks like this:
![Alt text](<../.images/soap ui envelope.png>)

