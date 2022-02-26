# Serenity BDD with Rest Assured
Restful API testing is one of the most important parts of software quality assurance. This requires additional understanding of how the application works inside technically. Among all the open source tools available, this project focuses on Serenity BDD with Rest Assured.

## _Serenity BDD_
Serenity is an open source test automation frameworks that helps testers write the well-structured functional tests, and provides outstanding test report. The main advantage of Serenity reporting is that it provides a detailed specification of the tested feature.

- Eclipse IDE
- Java 8
- Maven
- Serenity BDD with Cucumber and Rest Assured
## Project Setup
- Create a new project by navigating to File -> New -> Project -> Maven Project
- Enter the groupId, artifactId and click on Finish
- Now update the properties section in Maven pom.xml
```sh
<properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <serenity.version>2.6.0</serenity.version>
        <serenity.cucumber.version>2.6.0</serenity.cucumber.version>
        <rest.assured.version>4.4.0</rest.assured.version>
        <junit.version>4.13.2</junit.version>
        <maven.compiler.plugin.version>3.8.1</maven.compiler.plugin.version>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <maven.surefire.plugin.version>3.0.0-M5</maven.surefire.plugin.version>
        <maven.failsafe.plugin.version>3.0.0-M5</maven.failsafe.plugin.version>
  </properties>
```      
- Add repositories and pluginRepository to Maven pom.xml
```sh
<repositories>
        <repository>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
            <id>central</id>
            <name>bintray</name>
            <url>https://jcenter.bintray.com</url>
        </repository>
    </repositories>
    <pluginRepositories>
        <pluginRepository>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
            <id>central</id>
            <name>bintray-plugins</name>
            <url>https://jcenter.bintray.com</url>
        </pluginRepository>
    </pluginRepositories>
```
- Add Serenity, Serenity Cucumber, Serenity Rest Assured and JUnit dependencies to POM.xml
```sh
<dependencies>
        <dependency>
           <groupId>net.serenity-bdd</groupId>
           <artifactId>serenity-core</artifactId>
           <version>${serenity.version}</version>
           <scope>test</scope>
       </dependency>
        <dependency>
           <groupId>net.serenity-bdd</groupId>
           <artifactId>serenity-cucumber6</artifactId>
           <version>${serenity.cucumber.version}</version>
           <scope>test</scope>
       </dependency>
       <dependency>
           <groupId>net.serenity-bdd</groupId>
           <artifactId>serenity-screenplay-rest</artifactId>
           <version>${serenity.version}</version>
           <scope>test</scope>
       </dependency>
       <dependency>
           <groupId>net.serenity-bdd</groupId>
           <artifactId>serenity-rest-assured</artifactId>
           <version>${serenity.version}</version>
           <scope>test</scope>
       </dependency>
       <dependency>
           <groupId>io.rest-assured</groupId>
           <artifactId>rest-assured</artifactId>
           <version>${rest.assured.version}</version>
           <scope>test</scope>
       </dependency>
        <dependency>
           <groupId>junit</groupId>
           <artifactId>junit</artifactId>
           <version>${junit.version}</version>
           <scope>test</scope>
        </dependency>  
   </dependencies>
```
- Update Build Section of pom.xml
```sh
<build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>${maven.surefire.plugin.version}</version>
                <configuration>
                    <skip>true</skip>
                </configuration>
            </plugin>
            <plugin>
                <artifactId>maven-failsafe-plugin</artifactId>
                <version>${maven.failsafe.plugin.version}</version>
                <configuration>
                    <includes>
                        <include>**/*.java</include>
                    </includes>
                    <parallel>methods</parallel>
                    <useUnlimitedThreads>true</useUnlimitedThreads>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>integration-test</goal>
                            <goal>verify</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>${maven.compiler.plugin.version}</version>
                <configuration>
                    <source>${maven.compiler.source}</source>
                    <target>${maven.compiler.target}</target>
                </configuration>
            </plugin>
           <plugin>
               <groupId>net.serenity-bdd.maven.plugins</groupId>
               <artifactId>serenity-maven-plugin</artifactId>
               <version>${serenity.version}</version>
               <dependencies> 
                  <dependency>
                       <groupId>net.serenity-bdd</groupId>
                       <artifactId>serenity-single-page-report</artifactId>
                       <version>${serenity.version}</version>
                  </dependency>                
               </dependencies>
               <configuration>
                   <tags>${tags}</tags>
                   <reports>single-page-html</reports> 
               </configuration>
               <executions>
                  <execution>
                      <id>serenity-reports</id>
                      <phase>post-integration-test</phase>
                      <goals>
                          <goal>aggregate</goal>
                      </goals>
                   </execution>
               </executions>
           </plugin>
        </plugins>
    </build>
</project>
```
## Project Structure

```sh
    .
    ├── src/main/java                
    ├── src/main/resources        
    ├── src/test/java                
    │   ├── runner                    #Runner class for Serenity Cucumber
    │   │   ├── RunCucumber.java   
    │   ├── steps   
    │   │   ├── StepDefReqres.java   #Step Definitions for the scenarios
    ├── src/test/resources  
    │   ├── features               
    │   │   ├── ReqresAPI.feature    #Feature  file for Serenity Cucumber
    ├── target 
    ├── pom.xml                      #Maven Configuration
    └── README.md
```
- `features` package has the *.feature file in which the descriptions are defined using Gherkins. (Cucumber BDD)
- Login, Get UserList, Update User and Delete User test cases are covered with both positive and negative scenarios.

### _ReqresAPI.feature_
```sh
Feature: ReqresAPI Demo

Scenario: Login - Success
Given Set the Login URL
When Enter the valid credentials
Then Verify the status code 200

Scenario: Login - Failure
Given Set the Login URL
When Enter the invalid Credentials
Then Verify the status code 400 for failure

Scenario: Get List of Users - Success
Given Send the endpoint URL for List users
Then Verify the user list status code 200

Scenario: Get List of Users - Failure
Given Send the endpoint URL for usersList
Then Verify the status code 400 for failure

Scenario: Update User - Success
Given Set the URL for Update user
When Send the valid details to be updated
Then Verify the status code 200

Scenario: Update User - Failure
Given Set the invalid URL for Update user
When Send the valid details to be updated
Then Verify the status code 404 for failure

Scenario: Delete User - Success
When Set the URL for Delete User
Then Verify the status code 204 for delete

Scenario: Delete User - Success
When Set the invalid URL for Delete User
Then Verify the status code 404 for delete
```

### _StepDefReqres.java_
`steps` package has the actual code for the descriptions defined in the feature file. GET, POST, PUT and DELETE request types are covered in this demo.
```sh
package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

public class StepDefReqres {
	public Response response;
	
	// Set the Login URL	
	@Given("Set the Login URL")
	public void loginURL() {
		RestAssured.baseURI = "https://reqres.in/api/login";
	}
	
	// Post the valid credentials as a JSON content type
	@When("Enter the valid credentials")
	public void enterCredentials() {
		response = SerenityRest
				.given()
				.contentType(ContentType.JSON).body("{\r\n"
						+ "    \"email\": \"eve.holt@reqres.in\",\r\n"
						+ "    \"password\": \"cityslicka\"\r\n"
						+ "}")
				.post();
	}
	// Verify the returned status code
	@Then("Verify the status code {int}")
	public void verifyStatusCode(int code) {
		SerenityRest.restAssuredThat(response -> response.statusCode(code));
	}

	// Post the invalid credentials as JSON content type
	@When("Enter the invalid Credentials")
	public void enterInvalidCredentials() {
		SerenityRest
				.given()
				.contentType(ContentType.JSON).body("{\r\n"
						+ "    \"email\": \"eve.holt@reqres.in\",\r\n"
						+ "}")
				.post();
	}
	
	// Verify the status code for Failure
	@Then("Verify the status code {int} for failure")
	public void verifyFailureStatusCode(int code) {
		SerenityRest.restAssuredThat(response -> response.statusCode(code));
	}
	
	// Set the URL for retrieving user list
	@Given("Send the endpoint URL for List users")
	public void loadUserListUrl () {
		RestAssured.baseURI = "https://reqres.in/api/users?page=2";
	}
	
	// Verify the status code for success scenario
	@Then("Verify the user list status code {int}")
	public void verifyUserListStatusCode(int code) {		
		response = RestAssured.get();
		response.then().statusCode(code).extract().response();
		response.prettyPrint();
	}
	
	// Set the URL for retrieving user list
	@Given("Send the endpoint URL for usersList")
	public void loadUsersListUrl () {
		RestAssured.baseURI = "https://reqres.in/api/unknown/23";
	}

	// Set the valid url for update user
	@Given("Set the URL for Update user")
	public void updateURL() {
		RestAssured.baseURI = "https://reqres.in/api/users/2";
	}
	
	// Send the valid user details to be update
	@When("Send the valid details to be updated")
	public void updateUserDetail() {
		SerenityRest.given().contentType(ContentType.JSON).body("{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}").put();
	}
	
	// Set the invalid URL for update user
	@Given("Set the invalid URL for Update user")
	public void invalidUpdateURL() {
		RestAssured.baseURI = "https://reqres.in/api/";
	}
	
	// Send the invalid details to be updated	
	@When("Send the invalid details to be updated")
	public void updateUserDetailInvalid() {
		SerenityRest.given().contentType(ContentType.XML).body("{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}").put();
	}
	
	// Set the valid url for delete user
	@When("Set the URL for Delete User")
	public void deleteUserURL() {
		RestAssured.baseURI = "https://reqres.in/api/users/2";
	}
	
	// Verify the status code for delete
	@Then("Verify the status code {int} for delete")
	public void deleteUser(int code) {
		SerenityRest.delete().then().statusCode(code);
	}
	
	// Set the invalid url for delete user
	@When("Set the invalid URL for Delete User")
	public void deleteInvalidURL() {
		RestAssured.baseURI = "https://reqres.in/api/";
	}
}

```
### _RunCucumber.java_
`runner` package has the Runner file for CucumberWithSerenity. This is run by using the Junit. CucumberOptions annotation is required in which the feature (feature file path), steps (step definition package) and other attributes are added.
```sh
package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = {"src/test/resources/features/ReqresAPI.feature"},
					glue = {"steps"},
					plugin = { "pretty" }, 
					monochrome = true)
public class RunCucumber {

}
```
# Generate Serenity Report
The report is well formatted and contains consolidated results. Reporting is one of the major pillar in Serenity. Serenity Report not only reports on whether a test scenario passes or fails, but documents what it did, in a step-by-step narrative format.

Right click on the project. `Run As -> Maven Build`. Enter the below command in goals.
```sh
mvn clean verify
```
The test reports generated by Serenity are placed under target folder. Below 2 are the important reports that we need to focus on.
```sh
target/site/serenity/index.html
target/site/serenity/serenity-summary.html
```
By referring these reports, we can get the detailed statistics for each test scenario. 
