# Product Management API

This project aims to demo unit tests, integration tests, and cucumber tests.

## Usage/Examples
To run all the unit tests
```sh
./gradlew test --tests live.stoicism.productsampleapi.unit.ProductControllerTest
```

To run all the integration tests
```sh
./gradlew test --tests live.stoicism.productsampleapi.integration.ProductControllerIntegrationTest
```

To run the cucumber tests
```sh
./gradlew test --tests live.stoicism.productsampleapi.bdd.CucumberIntegrationTestRunner
```

To build the docker image
```sh
./build-image.sh
```

To start from docker compose
```sh
docker compose -f ./src/main/docker/app.yml up -d
```

To stop from docker compose
```sh
docker compose -f ./src/main/docker/app.yml down
```

To show allure report [Must install allure locally first](https://docs.qameta.io/allure/#_installing_a_commandline)
```sh
allure serve build/allure-results
```


### [Click here to access swagger ui](http://localhost:8080/swagger-ui/index.html#/)