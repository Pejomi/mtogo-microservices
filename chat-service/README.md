# Chat Service

This is our first draft of the chat service. It is a simple chat service that allows users to send messages to a MTOGO
customer service agent. We are using MongoDB to demonstrate how to use a NoSQL database with Spring Boot.

For training purposes, we added the html and css files to the backend project. This is not a good practice and should not be
done in production.

### Lobby
![Chat Lobby](<.images/mtogo_chat_lobby.PNG>)

### Chat room
![Chat room](<.images/mtogo_chat.PNG>)



## Getting Started

### Prerequisites
You need to have MongoDB installed and running on your machine. You can download it from [here](https://www.mongodb.com/download-center/community) or making a docker container.

We also recommend using Mongo Express for viewing the database.

Run the `docker-compose.yml` in the root folder of the chat-service project. This will set up a MongoDB and a Mongo Express container.


### Run the Service
Start the chat-service by running the `ChatServiceApplication` class.

Goto `http://localhost:8089` to see the chat lobby.

Goto `http://localhost:8181` to see the Mongo Express dashboard.
(default username: `admin`, default password: `pass`)
