# ADR-001-002: Inter-Module Communication

* **Status:** Accepted
* **Date:** 2025-08-16
* **Context:** As we are building a microservices architecture (ADR-002), our independently deployed services require a well-defined, network-based protocol to communicate with each other. This protocol must be efficient, strongly-typed, and enforce clear API contracts.
* **Decision:** All synchronous communication between services (e.g., `authn-service` to `notifications-service`) must happen via network calls using **gRPC**. Services will define their public APIs in `.proto` files. Direct database access between services is strictly forbidden.
* **Consequences:**
    * **Positive:**
        * **Enforces Strong Boundaries:** Network separation enforces true decoupling and independent deployment.
        * **Language-Agnostic Contracts:** `.proto` files create a clear, strongly-typed, and language-independent contract for each service.
        * **High Performance:** gRPC is efficient and well-suited for inter-service communication.
    * **Negative / Trade-offs:**
        * **Distributed System Complexity:** We must now handle the inherent challenges of network communication, including latency, fault tolerance (e.g., retries, circuit breakers), and distributed tracing.
        * **Serialization Overhead:** Data must be marshalled and unmarshalled between objects and Protobuf messages.