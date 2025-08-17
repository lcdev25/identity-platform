# ADR-006: Event-Driven Cache Management

* **Status:** Accepted
* **Date:** 2025-08-16
* **Context:** Several services require a high-performance read cache (Redis) to reduce database load and improve latency. Directly writing to both the database and Redis in the same operation would re-introduce the "dual-write problem" (addressed for Kafka in ADR-002-001), leading to potential cache inconsistency.
* **Decision:** We will manage all cache updates asynchronously using our event-driven architecture.
    1.  Services will **never** write directly to the cache during a business transaction. They will only write to their own database and publish a factual event (e.g., `UserProfileUpdated`) via the transactional outbox.
    2.  A dedicated, independent consumer (e.g., a "cache-handler") will subscribe to these events. Its sole responsibility is to receive the event and perform the appropriate `SET` or `DEL` operation in Redis to keep the cache eventually consistent with the source of truth (the database).
* **Consequences:**
    * **Positive:**
        * **Consistent Caching Strategy:** Applies the same reliability pattern (outbox) to caching as to all other inter-service communication.
        * **Improved Service Resilience:** A primary service's API is not impacted by the availability of the Redis cache.
        * **Decoupled Responsibility:** The service owning the data is not responsible for managing the cache; this is handled by a separate, focused consumer.
    * **Negative / Trade-offs:**
        * **Cache Latency:** The cache will be stale for a short period between the database commit and the event being processed by the cache handler. This eventual consistency must be acceptable for the use case.
