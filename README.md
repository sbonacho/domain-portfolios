# Microservice Portfolios

This microservice implements all logic operations of portfolios
  

# Running Microservice

```
mvn package
java -jar target/ch-create-client-0.1.0.jar
```

or

```
mvn spring-boot:run
```

## The run.sh Script

This script is used to wrap how to start/stop the microservice. Write the way you want to start/stop the microservice

# Docker Generation

```
mvn install dockerfile:build
```

# Run the service

This command starts the service with domain-portfolios name

```
docker run --rm -dit --name domain-portfolios soprasteria/domain-portfolios
```

Watching logs

```
docker logs domain-portfolios -f
```

Stopping the service

```
docker stop domain-portfolios
```

# Issues

- java.lang.NoSuchMethodError: org.springframework.util.Assert.state(ZLjava/util/function/Supplier;)V

Solved: Update to 2.0.0.M7 of spring-boot and 2.1.0.RC1 of spring-kafka adaptor.

- If spring boot starts and kafka is not up
    - 1. There is no error.
    - 2. If after that kafka starts CreateService never gets recovered. Restart service is needed.

# TODO Tasks

- Test domain event: PortfolioDelete