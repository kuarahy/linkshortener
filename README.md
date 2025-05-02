# Link Shortener

Solventum Code Challenge.

## Requirements
- Java 17+
- Maven 3.6+

## Building the Application

1. Clone the repository
2. Navigate to the location via Terminal
3. Build the project:
```bash
mvn clean install
```

## Running the Application
```bash
mvn spring-boot:run
```

### Stack Required:
- Java 
- Spring

### Stack that I want to use:
- Java
- Spring Boot
- React for front-end
- NPM to connect front-end and back-end
- Maven for running the project
- Vercel for deployment
- Hopefully I can use one of my parked domains for this

### ToDo: </br>
☑️ Java Algorithm </br>
☑️ Spring Boot </br>
☑️ Achieve Successfull Build </br>
☑️ Tests </br>
☑️ Pass All Tests </br>
❌ React Front-End </br>
❌ Axios for API connection </br>
❌ Vercel Deployment </br>

## Running Tests:
```bash
   mvn test
```

### Tests included </br>
Unit Tests for the encoding/decoding algorithm </br>
Service Tests for business logic </br>
API Tests for HTTP endpoints </br>
Rate Limiting Tests for concurrency control </br>
CLI Integration Tests for user interaction </br>
Special Feature Test for the easter egg </br>


## Endpoint Tests:
Open Terminal, run the application, paste this on Terminal:
```
curl -X POST http://localhost:8080/api/encode ^
  -H "Content-Type: application/json" ^
  -d "{\"url\":\"https://youtu.be/EL-D9LrFJd4?si=AIsAmo_qNp5V3-SU\"}"

curl -X POST http://localhost:8080/api/decode ^
  -H "Content-Type: application/json" ^
  -d "{\"url\":\"http://short.est/b6QMFT\"}"
```

It's atomic, 1:1 bijection. 

As it goes, decode will just work if you already encoded a URL during that run of the application since the links aren't persistent through runs (no database, or persistent memory of any type).

