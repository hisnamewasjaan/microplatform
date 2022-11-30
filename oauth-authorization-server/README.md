### Relevant Articles:

- [Keycloak Embedded in a Spring Boot Application](https://www.baeldung.com/keycloak-embedded-in-spring-boot-app)

Our Authorization Server will be pre-configured with baeldung-realm.json. Let's see a few relevant configurations in the file:

- users: our default users would be john@test.com and mike@other.com; they'll also have their credentials here
- clients: we'll define a client with the id newClient
- standardFlowEnabled: set to true to activate Authorization Code Flow for newClient
- redirectUris: newClient‘s URLs that the server will redirect to after successful authentication are listed here
- webOrigins: set to “+” to allow CORS support for all URLs listed as redirectUris

The Keycloak server issues JWT tokens by default, so there is no separate configuration required for that. Let's look at the Maven configurations next.


Access admin [http://localhost:8083/auth/](http://localhost:8083/auth/)
