# MyRetailService

Sample application to demonstrate rest api, backend rest api consumption & mongo connection

$cd MyRetailService

$mvn clean install, WAR Can be deployed to any container

$mvn site, to check unit test and cobertura reports

Add these jvm args to server classpath 
 -Dspring.profiles.active=E1 -DLog4jContextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector
 
Uses Log4j2 async logger, Mockito and PowerMock for stubbed unit testing
 
Enhancements to include 
1. Docker image to run on containers
2. Adding integration & functional tests
3. Refactoring as the codebase grows, eventually model & DTO seperation