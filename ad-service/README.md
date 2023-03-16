
### Spring Boot
version 2.7.5

[Spring Boot Reference Documentation 2.7.5](https://docs.spring.io/spring-boot/docs/2.7.5/reference/html/)


[Spring Boot Reference Documentation 3.0.2 - current](https://docs.spring.io/spring-boot/docs/current/reference/html/index.html)

#### data initialization

[howto.data-initialization](https://docs.spring.io/spring-boot/docs/2.7.5/reference/html/howto.html#howto.data-initialization)
```env
SPRING_JPA_HIBERNATE_DDL_AUTO: update
```

#### spring profiles

```env

```

### Gradle Java toolchain
[Toolchains for JVM projects](https://docs.gradle.org/current/userguide/toolchains.html)

Inspect using
```shell
gradle -q javaToolchains
```

### Testing
```shell
./gradlew test
```
more test inspiration https://thepracticaldeveloper.com/guide-spring-boot-controller-tests/#springboottest-approach---conclusions

Use the following to print exception stacktraces from tests in console
```shell
./gradlew test --info
```

[Spring security testing](https://docs.spring.io/spring-security/site/docs/5.2.0.RELEASE/reference/html/test.html)

### Running locally
```shell
./gradlew bootRun
```
[http://localhost:8081/api/ads](http://localhost:8081/api/ads)


### Open API

[http://localhost:8081/v3/api-docs](http://localhost:8081/v3/api-docs)
[http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)

### H2 console

[Spring Boot With H2 Database](https://www.baeldung.com/spring-boot-h2-database)


[http://localhost:8081/h2-console](http://localhost:8081/h2-console)

### JPA


[Power of Value Objects](https://medium.com/javarevisited/spring-boot-power-of-value-objects-a98831cfe4b8)

#### Primary keys

@IdClass vs @EmbeddedId [Composite Primary Keys in JPA](https://www.baeldung.com/jpa-composite-primary-keys)

#### Base entity
[Building Aggregates with Spring Data](https://dev.to/peholmst/building-aggregates-with-spring-data-2iig)


[Domain Driven Design: Storing Value Objects in a Spring Application with a Relational Database](https://medium.com/@benoit.averty/domain-driven-design-storing-value-objects-in-a-spring-application-with-a-relational-database-e7a7b555a0e4)

Github:
[Domain Driven Design: Entities, Value Objects, Aggregates and Roots with JPA](https://github.com/simbo1905/root-objects)

Fix @OneToOne details...

[One-to-One Relationship in JPA](https://www.baeldung.com/jpa-one-to-one)


Embeddables cannot be made truly immutabele


#### equals and hashcode

[The best way to implement equals, hashCode, and toString with JPA and Hibernate](https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/)

[How to implement equals and hashCode using the JPA entity identifier (Primary Key)](https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/)

#### UUID as primary key

Using the TSID as a Primary Key value [The best UUID type for a database Primary Key](https://vladmihalcea.com/uuid-database-primary-key/)

### lombok

@Builder
Not ideal for a JPA entries
[https://github.com/projectlombok/lombok/wiki/FEATURE-IDEA:-%22Mandatory%22-fields-with-@Builder](https://github.com/projectlombok/lombok/wiki/FEATURE-IDEA:-%22Mandatory%22-fields-with-@Builder)

### Actuator

[spring-boot/docs/2.7.5/reference/html/actuator](https://docs.spring.io/spring-boot/docs/2.7.5/reference/html/actuator.html)

Some examples..

- [/actuator discovery](http://localhost:8081/actuator)
- [/actuator/health](http://localhost:8081/actuator/health)
- [/actuator/health/db](http://localhost:8081/actuator/health/db)
- [/actuator/configprops](http://localhost:8081/actuator/configprops)
