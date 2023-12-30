const { GraphQLServer } = require('graphql-yoga')
const fetch = require('node-fetch')

//const baseURL = ``

const resolvers = {
    Query: {
        restaurant: (parent, args) => {
            const { id } = args
            return fetch(`http://localhost:8083/api/restaurant/${id}`).then(res => res.json())
        }
    },
}

const server = new GraphQLServer({
    typeDefs: './src/schema.graphql',
    resolvers,
})

server.start(() => console.log(`Server is running on http://localhost:4000`))