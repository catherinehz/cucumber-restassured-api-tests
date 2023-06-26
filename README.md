# RestAssured-Cucumber API automation framework

## **Required Setup :**

To use this framework, ensure that you have the following requirements in place:

- Java should be installed and properly configured on your machine.
- Maven should be installed and properly configured on your machine.
- Cucumber plugin should be added to your development environment.
- Open the command prompt and navigate to the folder where the pom.xml file is located.
- Run the following Maven command:
```
 mvn clean install
```
## **Running Test:**
All tests are written in the BDD style using Cucumber, they are located under the folder:
```
src/test/resources/features/Bookings.feature
```
In order to run tests you can execute class: 
```
CucumberTestRunner 
```
or simply execute require test from the file:
```
Bookings.feature
```


 