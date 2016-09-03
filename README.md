# MyRetailService

Sample application to demonstrate rest api, backend rest api consumption & mongo connection

$ git clone https://github.com/agyan2/MyRetailService.git

$ cd MyRetailService

$ mvn site
-- Unit Test and coverage reports can be accessed in target/site/index.html

$ mvn clean install 
-- MyRetailService-0.0.1-SNAPSHOT.war Can be deployed to any container

$ mkdir /logs/myretailservice

Install MongoDB
$ cd mongodb

Initialize mongo database service
$ mongod.exe --dbpath <path to db>

Install tomcat, install WAR on tomcat, copy to tocat/webapps

Add these jvm args to server classpath 
 -Dspring.profiles.active=E1 -DLog4jContextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector

Start server
$<tomcat bin> startup.bat/sh

Path
<host:port>/MyRetailService/api/v1/products -- get all products
<host:port>/MyRetailService/api/v1/products/15117729 -- get product for given id, put to update price

For functional testing
Open Postman
Import from file, choose MyRetailService-Tests.postman_collection.json
Run the test cases

Enhancements to include 
1. Docker image to run on containers
2. Adding curl kind of functional tests, eventually can be tied through some jenkins job
3. Refactoring as the codebase grows, eventually model & DTO seperation