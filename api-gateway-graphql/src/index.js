const { GraphQLServer } = require('graphql-yoga')
const fetch = require('node-fetch')

//const baseURL = ``

const resolvers = {
    Query: {
        // restaurant-service
        restaurantById: (parent, args) => {
            const { id } = args
            return fetch(`http://localhost:8083/api/restaurant/${id}`).then(res => res.json())
        },
        restaurantsByZipcode: (parent, args) => {
            const { zipCode } = args
            return fetch(`http://localhost:8083/api/restaurant/zip/${zipCode}`).then(res => res.json())
        },
        restaurantsByCity: (parent, args) => {
            const { city } = args
            return fetch(`http://localhost:8083/api/restaurant/city/${city}`).then(res => res.json())
        },
        activeRestaurantsByZipcode: (parent, args) => {
            const { zipCode } = args
            return fetch(`http://localhost:8083/api/restaurant/active/zip/${zipCode}`).then(res => res.json())
        },
        activeRestaurantsByCity: (parent, args) => {
            const { city } = args
            return fetch(`http://localhost:8083/api/restaurant/active/city/${city}`).then(res => res.json())
        },
        pendingRestaurants: () => {
            return fetch(`http://localhost:8083/api/restaurant/pending`).then(res => res.json())
        },

        // menu-service
        menuById: (parent, args) => {
            const { id } = args
            return fetch(`http://localhost:8084/api/menu/${id}`).then(res => res.json())
        },
        menusByRestaurantId: (parent, args) => {
            const { id } = args
            return fetch(`http://localhost:8084/api/menu/restaurant/${id}`).then(res => res.json())
        },

        // courier-service
        couriers: () => {
            return fetch(`http://localhost:8087/api/couriers`).then(res => res.json())
        },
        courierById: (parent, args) => {
            const { id } = args
            return fetch(`http://localhost:8087/api/couriers/${id}`).then(res => res.json())
        },

        // order-service
        orderById: (parent, args) => {
            const { id } = args
            return fetch(`http://localhost:8080/api/order/${id}`).then(res => res.json())
        },
        ordersByRestaurantId: (parent, args) => {
            const { id } = args
            return fetch(`http://localhost:8080/api/order/restaurant/${id}`).then(res => res.json())
        },
        createdOrdersByRestaurantId: (parent, args) => {
            const { id } = args
            return fetch(`http://localhost:8080/api/order/restaurant/created/${id}`).then(res => res.json())
        },
        activeOrdersByRestaurantId: (parent, args) => {
            const { id } = args
            return fetch(`http://localhost:8080/api/order/restaurant/active/${id}`).then(res => res.json())
        },
        declinedOrdersByRestaurantId: (parent, args) => {
            const { id } = args
            return fetch(`http://localhost:8080/api/order/restaurant/declined/${id}`).then(res => res.json())
        }
    },
    Mutation: {
        // restaurant-service
        approveRestaurantById: (parent, args) => {
            const { restaurantId } = args
            return fetch(`http://localhost:8083/api/restaurant/approve/${restaurantId}`, {method: "PUT"}).then(res => res.json())
        },
        rejectRestaurantById: (parent, args) => {
            const { restaurantId, reason } = args
            return fetch(`http://localhost:8083/api/restaurant/reject/${restaurantId}?reason=${reason}`, {method: "PUT"}).then(res => res.json())
        },

        // courier-service
        createCourier: (parent, args) => {
            const { courierDto } = args
            return fetch(`http://localhost:8087/api/couriers`, {
                method: "POST",
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify({
                    "vehicle": courierDto.vehicle,
                    "firstname": courierDto.firstname,
                    "lastname": courierDto.lastname,
                    "country": courierDto.country,
                    "zipCode": courierDto.zipCode,
                    "phone": courierDto.phone,
                    "email": courierDto.email,
                    "active": courierDto.active
                })
            }).then(res => res.json())
        },
    }
}

const server = new GraphQLServer({
    typeDefs: './src/schema.graphql',
    resolvers,
})

server.start(() => console.log(`Server is running on http://localhost:4000`))