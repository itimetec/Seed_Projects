# Template for Java Microservices project

# Pre-requisite 

1. Java 8 Runtime
2. Mongo DB

# Eclipse (latest available)

1. Install [Spring Tools ](https://marketplace.eclipse.org/content/spring-tools-aka-spring-ide-and-spring-tool-suite)
2. Import settings/Cartos_Profile.xml from Eclipse Settings available under java->Code Style -> Formatter -> import
3. Install Lombok plugin 
	3.1 Download lombok.jar from https://projectlombok.org/download
	3.2 $java -jar lomobok.jar will launch the installer to install the plugin.
	3.3 if the above does not work, then modify the eclipse.ini file to include -javaagent:lomobk.jar and place the lomobok.jar at the same location as the eclipse.exe

# IDEA(Intellij)

If you are using IDEA, please follow these instructions, note that you should have Spring license for IDEA already.

1. Go to file -> New -> Project from Existing sources.
2. Select project from file system.
3. Select gradle as build tool in "import module" dialog.
4. Install Lombok plugin
    4.1 Go to settings -> plugin
    4.2 Search for "lombok" and install it.
5. Go to settings -> "build, execution and deployment" -> compiler -> Annotation Processors.
    5.1 Enable checkbox for "Enable annotation processing" and apply settings.

# Instructions

1. Create a user in MongoDB, use the code available in scripts/createMongoUser.sh
2. Configure the MongoDB credentials in config/application.properties appropriately if you want to change it.
3. Setup the environment using setenv.sh or setenv.bat depending on the platform. i.e. $. ./setenv.sh or CMD>setenv.bat
3. Build and run the code(use build.bat or build.sh scripts folder depending on the platform), the build script would do the following.
    * Check the code for style errors and executes unit tests

        ./gradlew check
 

    * Execute unit tests
       ./gradlew runUnitTests
    * Build and Run the code

        ./gradlew bootRun

4. A sample REST API will be exposed on http://localhost:8080/ and can be invoked as follows
        http://localhost:8080/books/list?pageNumber=1
   
        Will return list of books properties, 
        
        other api and their corresponding usage can be found on swagger ui
        
5. Swagger UI URL: http://localhost:8080/swagger-ui.html

6. Swagger UI docs: http://localhost:8080/v2/api-docs
   
7. By default project suspports gradle builds, in case you want to use Maven for builds you can generate a POM.xml as follows

        ./gradlew writeNewPom

8. A sample integration test is included in the project to demonstrate how to use a 3rd party REST api, as an example the API
exposed by the sample project is excersized. In order to run the integration tests do the following

	$./gradlew bootRun 

	open another window

	$./gradlew integrationTests

9. Creating the distribution, this command will create a dist folder containing the jar, version.txt and start.sh startup script
	$./gradlew -x test build

# Eclipse

1. Update the eclispe project based on the build.gradle run following command

        ./gradlew eclipse

2. Import the project in Eclipse as an existing project.

#Details
1. The project is based on Spring framework and uses Spring Boot to bootstrap the system.
2. The project has sample unit tests for all layers, i.e repository, service as well as controller
3. The coverage report is generated and placed in  build/reports/jacoco/html
4. The checkstyle report is placed in build/reports/checkstyle
5. The unit tests report is placed in build/reports/tests

Notes: 

1. For unit tests embedded mongoDB is used and the database is created in mongo_embedded folder
2. Test cases must follow AAA(Arrange/Act/Assert) pattern which makes it easy to write good unit tests.




