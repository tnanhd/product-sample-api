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