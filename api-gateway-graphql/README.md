# Api-gateway-graphQL

## Introduction
This is a implementation of an api-gateway using GraphQL. The api-gateway is a part of the MTOGO-application project created by the group Pejomi.

## How to run the project

```
    npm start
    npm run build
    npm start
```

### Use the GraphQL playground
The GraphQL playground is a tool for testing queries and mutations. It can be accessed by going to http://localhost:4000.

#### Example of a query to use
This query will get a restaurant by id
```
    query {
        getRestaurantById(id: 1) {
            id
            name
            address
            zipCode
            city
            phoneNumber
            emai
        }
    }
```
