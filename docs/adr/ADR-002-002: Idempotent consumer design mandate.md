# ADR-007: Idempotent Consumer Design Mandate

* **Status:** Accepted
* **Date:** 2025-08-16
* **Context:** The use of the Transactional Outbox pattern (ADR-002-001) provides an "at-least-once" delivery guarantee for our events. This means that under certain failure scenarios (e.g., a consumer crashing after processing a message but before committing its offset), a message may be redelivered. This requires a strict contract for how consumers are designed.
* **Decision:** All event consumers across the entire platform **must be idempotent**. An operation is idempotent if the result of performing it once is the same as the result of performing it multiple times. Consumers must implement one of the following strategies to achieve this:
    1.  **Business Key Uniqueness:** Use a unique identifier from the event payload as the primary key or a `UNIQUE` constraint in the database. A duplicate `INSERT` will safely fail.
    2.  **Optimistic Locking:** Use a version number or event timestamp in `UPDATE` statements to prevent processing stale or duplicate events.
    3.  **Processed Event ID Tracking:** Maintain a dedicated record of processed event IDs and check against it before executing business logic.
* **Consequences:**
    * **Positive:**
        * **System Reliability:** Makes the entire event-driven system resilient to failures and message redeliveries, preventing data corruption or incorrect state.
        * **Simplified Error Handling:** Consumers can safely retry operations without needing complex logic to determine if an operation has already been completed.
    * **Negative / Trade-offs:**
        * **Increased Design Overhead:** Developers must consciously design for idempotency from the start; it is not an automatic feature.
        * **Potential for Minor Performance Impact:** Strategies like checking for processed event IDs add a small amount of overhead to each event's processing.