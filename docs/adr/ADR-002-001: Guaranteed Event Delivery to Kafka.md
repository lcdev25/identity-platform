# ADR-002-001: Guaranteed Event Delivery to Kafka

* **Status:** Accepted
* **Date:** 2025-08-16
* **Context:** Our services need to publish events to Kafka after making changes to the database. A standard transaction cannot atomically encompass both a database commit and a message broker publish. This "dual-write problem" creates a risk of data inconsistency: the database write could succeed while the Kafka publish fails, leaving other services unaware of a critical state change.
* **Decision:** We will implement the **Transactional Outbox pattern** to ensure reliable, transactional event delivery.
    1.  Within the same database transaction as the business operation (e.g., creating a user), an event record will be inserted into a dedicated `outbox_events` table.
    2.  A separate, asynchronous process will monitor this `outbox_events` table, read new event records, publish them to the appropriate Kafka topic, and then mark the records as processed.
* **Consequences:**
    * **Positive:**
        * **Guaranteed At-Least-Once Delivery:** Ensures that if a business transaction commits, its corresponding event will eventually be published to Kafka, even if the broker is temporarily unavailable.
        * **Prevents Data Inconsistency:** Eliminates the dual-write problem, guaranteeing that our service's internal state and the public event stream do not diverge.
        * **Increased Resilience:** The primary business transaction is decoupled from the message broker's availability.
    * **Negative / Trade-offs:**
        * **Increased Complexity:** Introduces a new database table (`outbox_events`) and a separate relaying component (e.g., a polling service or a Change Data Capture connector like Debezium).
        * **Eventual Consistency:** A small amount of latency is introduced between the database commit and the event's appearance in Kafka.
        * **Requires Idempotent Consumers:** This pattern guarantees at-least-once delivery, meaning downstream consumers must be designed to handle potential duplicate messages gracefully.