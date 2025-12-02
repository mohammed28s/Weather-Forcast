# Weather Forecast Application - AI Coding Agent Instructions

## 🎯 Project Overview

A Spring Boot REST API that fetches real-time weather data from Open-Meteo API and exposes it through a standardized endpoint. The application demonstrates best practices for external API client integration, error handling, and layered architecture.

**Key Stack**: Java 25, Spring Boot 3.5.7, Spring RestClient, Lombok

## 🏗️ Architecture & Data Flow

### Layer Structure
- **Controller** (`weather.com.Controller`): REST endpoint `/api/weather/current` accepts latitude/longitude parameters
- **Service** (`weather.com.Service.WeatherService`): Orchestrates business logic, delegates to API client
- **Client** (`weather.com.ClientAPI.WeatherApiClient`): Wraps Spring RestClient, calls Open-Meteo API
- **DTO** (`weather.com.DTO`): Two-layer mapping strategy (see below)
- **Configuration** (`weather.com.Configuration`): RestClient bean setup, externalized config via `WeatherProperties`

### Key Pattern: DTO Mapping
The application uses **two distinct DTOs** to decouple external API contracts from internal API responses:
1. `WeatherApiResponse` - Maps Open-Meteo's external JSON structure (with `@JsonProperty` annotations for field name mapping)
2. `WeatherResponse` - Application's standardized response contract exposed to clients

**Why this matters**: Changes to Open-Meteo's API response don't require controller/client contract changes.

### External API Integration
- **Endpoint**: `https://api.open-meteo.com/v1/forecast`
- **Parameters**: Latitude/Longitude as query string, `current_weather=true` flag
- **Base URL**: Externalized in `application.yaml` as `weather.api.base-url`
- **Client**: Spring RestClient (Spring 6+) - prefer RestClient over deprecated RestTemplate

## ⚙️ Configuration & Build

### Build & Run
```bash
# Maven wrapper (macOS/Linux)
./mvnw clean package
./mvnw spring-boot:run

# Windows
mvnw clean package
mvnw spring-boot:run
```

### Configuration File
- `src/main/resources/application.yaml` - Controls `weather.api.base-url` and other externalized properties
- `WeatherProperties` class binds `weather.*` prefix from YAML using `@ConfigurationProperties`

### Maven Setup
- **Parent**: Spring Boot 3.5.7 starter-parent
- **Key Dependencies**: 
  - `spring-boot-starter-web` (REST controllers)
  - `spring-boot-starter-validation` (input validation)
  - `lombok` (annotation processor - configured in pom.xml)
  - `spring-boot-starter-test` (JUnit, Mockito)

## 🔑 Project-Specific Conventions

### Exception Handling
- **Pattern**: `GlobalExceptionHandler` with `@ControllerAdvice` + `@RestControllerAdvice` (redundant but present)
- **Response Format**: RFC 7807 `ProblemDetail` (Spring's standard)
- **Custom Exceptions**: Defined as static inner classes in `GlobalExceptionHandler` (e.g., `ResourceNotFoundException`, `BadRequestException`)
- **Always include**: timestamp, path, detail in ProblemDetail responses
- **Location**: `weather.com.ExceptionHandlings.GlobalExceptionHandler`

**Example**: When latitude/longitude params are missing, controller throws `ResourceNotFoundException` with message "Add the lat and lon parameters"

### Logging
- **Pattern**: Slf4j with LoggerFactory (`org.slf4j.LoggerFactory`)
- **Current Practice**: Logger created as instance field in controllers (see `WeatherController`)
- **Note**: Multiple log levels are invoked in `getWeather()` method (trace, debug, info, warn, error) - standardize this for production

### Dependency Injection
- **Preference**: Constructor injection with `@RequiredArgsConstructor` (Lombok) + `@Autowired` on fields (current hybrid approach)
- **Improvement Needed**: Eliminate field-level `@Autowired` in favor of constructor injection alone (Lombok handles constructor generation)

## 🔄 Adding New Features

### Adding a New Weather Endpoint
1. Add new method to `WeatherApiClient.getWeather()` following the pattern: construct URL → call RestClient → map response DTOs
2. Create new `WeatherResponse` field if needed (no need for new external DTO if Open-Meteo already provides the data)
3. Add endpoint to `WeatherController` under `/api/weather/{new-endpoint}`
4. Add exception handling in `GlobalExceptionHandler` if new failure modes exist
5. Update `application.yaml` with any new externalized parameters

### Adding External API Configuration
1. Add new properties to `application.yaml` under `weather:` namespace
2. Extend `WeatherProperties` with `@ConfigurationProperties` binding
3. Inject via constructor in `WeatherApiClient` instead of `@Value` (more flexible)

## 🧪 Testing
- Test directory: `src/test/java/weather/com/`
- Framework: JUnit (via spring-boot-starter-test)
- Current test class: `ComApplicationTests` (minimal setup, no active test cases)
- **To add**: Mock `RestClient` responses, test `WeatherService`, test exception scenarios

**Run tests**: `./mvnw test`

## 📌 Anti-Patterns to Avoid

1. **Field Injection**: Replace `@Autowired` field injection with constructor injection (Lombok + `@RequiredArgsConstructor`)
2. **Hard-coded URLs**: Always externalize API endpoints via `application.yaml` and `@ConfigurationProperties`
3. **Broad Exception Handlers**: Keep `GlobalExceptionHandler` focused; add specific handlers for distinct error types
4. **Missing Validation**: Use `@Valid` + `@NotNull` / `@Min` / `@Max` for request parameters
5. **Unmapped DTO Fields**: Ensure all fields in external API responses are explicitly mapped to `WeatherResponse` (document omissions)

## 📁 Key Files Reference

| Path | Purpose |
|------|---------|
| `src/main/java/weather/com/WeatherApplication.java` | Spring Boot entry point with `@SpringBootApplication` |
| `src/main/java/weather/com/Controller/WeatherController.java` | REST endpoint `/api/weather/current` |
| `src/main/java/weather/com/Service/WeatherService.java` | Business orchestration layer |
| `src/main/java/weather/com/ClientAPI/WeatherApiClient.java` | RestClient wrapper for Open-Meteo API |
| `src/main/java/weather/com/DTO/WeatherResponse.java` | Application response contract |
| `src/main/java/weather/com/DTO/WeatherApiResponse.java` | External API response mapping |
| `src/main/java/weather/com/Configuration/RestClientConfig.java` | RestClient bean factory |
| `src/main/java/weather/com/ExceptionHandlings/GlobalExceptionHandler.java` | Centralized error responses |
| `src/main/resources/application.yaml` | Configuration & externalized properties |

## 🔗 External Dependencies
- **Open-Meteo API**: Free weather data, no authentication. URL: `https://api.open-meteo.com/v1/forecast`
- **Spring RestClient**: Declarative HTTP client (Spring 6+), replaces RestTemplate

---

**Last Updated**: December 2025 | **Java Version**: 25 | **Spring Boot**: 3.5.7
