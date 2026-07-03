# Spring Boot Redis Cache Setup

## 1. Run Redis in Docker

Start the Redis container:

```bash
docker run -d --name redis-cache -p 6379:6379 redis
```

This starts a Redis container named `redis-cache` and exposes it on port **6379**.

---

## 2. Enter the Redis CLI

Run the following command to access the Redis CLI:

```bash
docker exec -it redis-cache redis-cli
```

---

## 3. Verify Redis is Running

Inside the Redis CLI, execute:

```bash
PING
```

**Expected Output**

```text
PONG
```

A `PONG` response confirms that the Redis server is running successfully.

---

## 4. Fetch Cached Data

To retrieve a cached value from Redis:

```bash
GET <cache-name>::<key-name>
```

**Example**

```bash
GET weather::London
```

To view all available keys:

```bash
KEYS *
```

---

## 5. Configure Spring Boot

Add the following properties to your `application.properties` file:

```properties
spring.cache.type=redis
spring.data.redis.host=localhost
spring.data.redis.port=6379
spring.cache.cache-names=weather
```

---

## 6. Add Redis Dependency

### Maven

Add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

---

## 7. Enable Caching

Enable caching in your Spring Boot application:

```java
@SpringBootApplication
@EnableCaching
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

---

## 8. Example of `@Cacheable`

```java
@Service
public class WeatherService {

    @Cacheable(value = "weather", key = "#city")
    public String getWeather(String city) {
        System.out.println("Fetching weather data...");
        return "Weather data for " + city;
    }
}
```

### Behavior

- **First call**: Executes the method and stores the result in Redis.
- **Subsequent calls with the same key**: Returns the cached value without executing the method.

---

## 9. Verify Cache Entries

Inside the Redis CLI, run:

```bash
KEYS *
```

Example output:

```text
1) "weather::Pune"
```

---

## Interview Summary

> I integrated Redis caching into a Spring Boot application using `@EnableCaching` and `@Cacheable`. Redis was hosted in a Docker container on port **6379**, and Spring Boot was configured using `spring.cache.type=redis` along with the Redis host and port properties. Frequently accessed data was cached in Redis, reducing repeated database or external API calls and improving application performance.

---

## Notes

- Redis commands are **case-sensitive**.
- Use `GET <cache-name>::<key-name>` to retrieve cached values.
- Use `KEYS *` to list all cache keys (recommended only for development/testing).
- In production, avoid using `KEYS *` on large datasets because it can impact performance.