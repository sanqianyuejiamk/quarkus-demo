# quarkus-demo project

### Generate your application
Create a new app using code.quarkus.io.

```
https://code.quarkus.io/
```

### Start application
```
mvn quarkus:dev -Ddebug
```

### Test MicroProfile Fault Tolerance API

http://localhost:8080/hello

[结果1] - **Success**
```
{"cfops":[{"id":1001,"nome":"Success! 312ms","time":"312ms"}],"version":"3.0.0"}
```

[结果2] - **Failure**
```
{"cfops":[{"id":1001,"nome":"Failure! 2157ms","time":"2157ms"}],"version":"3.0.0"}
```

```java
@ApplicationScoped
public class FaultToleranceExample {
    
    @Asynchronous
    @Fallback(fallbackMethod = "fallback")
    @Retry(retryOn = {RuntimeException.class, TimeoutException.class},
            maxRetries = 1)
    @Timeout(1000)
    public Future<String> demonstrateFaultTolerance() {
        Random random = new Random();
        int sleepTime = random.nextInt(4000);
        
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }
        
        return CompletableFuture.completedFuture("Success!");
    }
    
    
    public Future<String> fallback() {
        return CompletableFuture.completedFuture("Failure!");
    }
}
```

### 主要知识点

- **Fault Tolerance 1.0** : Implements a collection of programming patterns like Bulkheads, Timeouts, Circuit Breakers, and Fallbacks to monitor and gracefully react to potential failure conditions. Utilizing these patterns can eliminate the potential for cascading failures in a microservices architecture.
- **Health Check 1.0** : Standard endpoint that exposes a custom-developed service’s health to the underlying platform. When running on OpenShift, health check probes can monitor this endpoint and restart the containers running an unhealthy service.
- **OpenTracing 1.0** : Enables tracing the flow of a request as it traverses multiple services within a microservices architecture. When Thorntail is used with Jaeger (a distributed tracing service), organizations can quickly track down performance bottlenecks.
- **Rest Client 1.0** : A type-safe API for invoking RESTful services.
- **JWT RBAC 1.0** : Using OpenID Connect (OIDC)-based JSON Web Tokens (JWT) for role-based access control of microservices endpoints.


>Java Fullstack<br>
>JAX-RS, 
>MicroProfile, 
>JSON-B, 
>GSON, 
>JWT

### Doc1 - Quarkus for Spring Developers
```
https://quarkus.io/blog/quarkus-for-spring-developers/
```