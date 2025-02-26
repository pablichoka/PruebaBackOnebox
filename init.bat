@echo off
echo Select an option:
echo 1. Run the application (endpoints are available at http://localhost:8080/swagger-ui/index.html)
echo 2. Run the tests
set /p choice=Enter your choice (1 or 2):

if "%choice%"=="1" (
  .\mvnw spring-boot:run
) else if "%choice%"=="2" (
  .\mvnw test
) else (
  echo Invalid option.
)