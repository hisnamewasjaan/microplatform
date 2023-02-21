# Microplatform

## Ad Service
[Ad Service](ad-service) for creating and managing Ads for items for sale

## Ui
[ui](ui) is an angular frontend

## Keycloak
Keycloak is used as authorization server using OIDC

## Postgres
Postgres is used as backend for Ad service and keycloak
Data is held in [data/db](data/db)

## Running
Using docker compose (see [compose file](docker-compose.yml))
```shell
docker compose up
```

### Keycloak

[keycloak instance](http://localhost:8083), See
[compose file](docker-compose.yml) for admin details

[Microads realm](http://localhost:8083/admin/master/console/#/microads)


Link to 'well-known' OpenID configuration endpoint inside running keycloak
instance
[microads/.well-known/openid-configuration](http://localhost:8083/realms/microads/.well-known/openid-configuration)


#### Manual setup steps
Only the first time when using import realm from json file.

Microads realm should have been imported.
- [http://localhost:8083/admin/master/console/#/microads](http://localhost:8083/admin/master/console/#/microads)



###### Create client roles:
Clients -> ads -> Roles -> Create role
- read
- write


###### Create client scope:
Client scopes -> Create client scope
- write

###### Add client scope to client:
Clients -> ads -> Client Scopes-> Add client scope
- write (bad name?)
  - scopes -> assign roles (filter by clients)
    - ads read
    - ads write


###### Create the first user
- [create user](http://localhost:8083/admin/master/console/#/microads/users)
  - username: john@test.com
  - passwd: 123
  - temporary: off
Role mapping (filter by client roles)
- ads read
- ads write

###### Verify

Clients -> ads -> Client scopes -> Evaluate
- user, john...
- Generated access token must contain "write"


###### in the Ad service
After authenticating that user, the following should be logged when performing
actions. Note the granted authorities:

```shell
INFO m.adservice.web.AdController : with authentication <JwtAuthenticationToken [Principal=org.springframework.security.oauth2.jwt.Jwt@bec88b03, Credentials=[PROTECTED], Authenticated=true, Details=WebAuthenticationDetails [RemoteIpAddress=172.27.0.1, SessionId=null], Granted Authorities=[SCOPE_openid, SCOPE_email, SCOPE_read, SCOPE_profile, SCOPE_write]]>

```





### DDD

https://www.domainlanguage.com/upcoming-events/
https://github.com/citerus/dddsample-core/tree/master/src/main/java/se/citerus/dddsample
https://groups.google.com/g/dddsample
https://www.dddcommunity.org/wp-content/uploads/files/pdf_articles/Vernon_2011_1.pdf
