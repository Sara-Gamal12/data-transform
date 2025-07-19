# 🛠️ Data Transform Service

A Spring Boot application designed to transform third-party raw key-value data into human-readable, localized output using configurable mappings and label definitions.

## Table of Contents
- [Features](#features)
- [Demo](#demo)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [How It Works](#how-it-works)
- [Running Tests](#running-tests)
- [Configuration (application.yml)](#configuration-applicationyml)
- [Swagger UI](#swagger-ui)
- [Design Patterns Used](#design-patterns-used)

## Features 
- Parses plain text input (`key : value` format)
- Supports multiple third-party systems (e.g., `party1`, `party2`)
- Language localization (currently supports English and Arabic)
- Easily configurable via `.yml` files (no hardcoded logic)
- Built using Strategy and Factory design patterns
- Swagger/OpenAPI documentation
- Unit tests included (uses `@SpringBootTest`)

---
## Demo 
- To quickly demonstrate the functionality, a sample input is hardcoded and processed in the `main()` method of the `DataTransformApplication` class.
-  The application also has a REST API at `/api/transform` where you can POST raw key-value data and receive a localized response.
![Recording2025-07-19052206-ezgif com-optimize](https://github.com/user-attachments/assets/d21705a2-e0e5-46cc-8261-4f2098cc0c99)

## Technologies Used
- Java 17
- Spring Boot 3
- Spring Web
- Spring Validation
- Springdoc OpenAPI (Swagger UI)
- Maven

---

## Project Structure
```
src/
├── main/
│ ├── java/
│ │ └── com.example.datatransform/
│ │ ├── config/ # Language and data config
│ │ ├── controller/ # REST API controller
│ │ ├── model/ # Label and ThirdPartyKeyMappings models
│ │ ├── repository/ # In-memory simulated database
│ │ ├── service/ # Transformation logic
│ │ │ ├── strategy/ # Strategy pattern implementation
│ │ └── utils/ # Utility classes (e.g., MessageBuilder)
│ └── resources/
│ └── application.yml # Configurable labels & mappings
│
├── test/
│ └── java/
│ └── com.example.datatransform/
│ └── (unit tests for service and strategy)
```

---

## How It Works
1. A POST request with raw key-value data (in plain text) is sent to `/api/transform`.
2. The source system and language are passed as query parameters.
3. The app:
   - Parses and normalizes the input
   - Looks up mappings for the source system
   - Translates the keys using the appropriate language
4. Returns a plain text response with localized keys.

---

## Running Tests
```
mvn clean test
```
### Tests cover:
- Successful transformation (EN/AR)
- Handling malformed input
- Strategy and factory logic

---

## Configuration (application.yml)
```
data:
  labels:
    - id: 1
      labelEn: Client Name
      labelAr: اسم العميل
    - id: 2
      labelEn: Mobile Number
      labelAr: رقم الموبايل
  mappings:
    - id: 1
      labelId: 1
      thirdPartyKey: client_name
      thirdPartySource: party1
    - id: 2
      labelId: 2
      thirdPartyKey: mobile_number
      thirdPartySource: party1
language:
  default: en
  supported:
    - en
    - ar
```

---

## Swagger UI
After running the application:
🔗 http://localhost:8080/swagger-ui.html
Explore and test your API visually.

---

## Design Patterns Used
- Strategy Pattern: Encapsulate different transformation algorithms for each third-party source.
  
      Each third-party system (e.g., party1, party2) provides data with different key formats. To handle this without cluttering the code with if-else or switch, a TransformationStrategy interface is used with multiple implementations.    

- Factory Pattern: Dynamically resolve the appropriate strategy based on the input source.

      A TransformationStrategyFactory uses Spring’s dependency injection to map source keys to their corresponding strategy beans.

- Builder Pattern: Create well-formatted, multi-line output messages from key-value pairs.

      The MessageBuilder class allows constructing the localized output message.

---

