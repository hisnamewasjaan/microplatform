## Keycloak

### Microads realm:

- users: our default users would be _john@test.com_ and mike@other.com; they'll also have their credentials here
- clients: we'll define a client with the id _ads_
- standardFlowEnabled: set to true to activate Authorization Code Flow for _ads_
- redirectUris: _ads_‘s URLs that the server will redirect to after successful
authentication are listed here
- webOrigins: set to “+” to allow CORS support for all URLs listed as redirectUris

The Keycloak server issues JWT tokens by default, so there is no separate configuration required for that.

Access admin [http://localhost:8083/auth/](http://localhost:8083/auth/)

## Users

- john@test.com / 123
- mike@other /
