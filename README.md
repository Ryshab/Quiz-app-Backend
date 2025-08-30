\# Quiz Microservices (Spring Boot)



Monorepo with three Spring Boot services:

\- service-registry (Eureka)

\- quiz-service

\- question-service



\## Prerequisites

\- Java (JDK) and Maven installed and on PATH





\## Project Structure

quiz-microservices/

├─ service-registry/     # Eureka server

├─ quiz-service/         # Quiz API

└─ question-service/     # Question API



Each subfolder is a standalone Spring Boot app with its own pom.xml.



\## Suggested Ports

\- service-registry: 8761

\- quiz-service: 8082

\- question-service: 8081



Set in each service's application.properties (or application.yml), for example:

server.port=8081





\## Build

From the repo root (build each service independently):

mvn -f service-registry/pom.xml -q clean package

mvn -f quiz-service/pom.xml -q clean package

mvn -f question-service/pom.xml -q clean package



\## Run (Maven)

In three terminals:



\# 1) Start Eureka

cd service-registry

mvn spring-boot:run



\# 2) Start question-service

cd question-service

mvn spring-boot:run



\# 3) Start quiz-service

cd quiz-service

mvn spring-boot:run



\## APPLICATIONS

1. CRUD applications in question service
2. create quiz, give response and get score in quiz service

   Simple application build to understand concept of microservice.
