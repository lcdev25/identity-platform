# ADR-001-001: Architectural Style - Microservices in a Monorepo

* **Status:** Accepted (Supersedes previous version)
* **Date:** 2025-08-16
* **Context:** We need an architectural style that enforces strong service boundaries from the start. While a modular monolith offers initial simplicity, we want to ensure services are fully decoupled and can be scaled, updated, and deployed independently to maximize team autonomy and system resilience.
* **Decision:** We will adopt a **microservices** architecture, with all services residing within a **single Git repository (a monorepo)**. Each top-level module (e.g., `authn-service`, `authz-service`) will be built and deployed as an independent service (e.g., a separate Docker container).
* **Consequences:**
    * **Positive:**
        * **Enforced Decoupling:** Services have strong, impassable boundaries.
        * **Independent Deployment:** A change in one service does not require re-deploying the entire system.
        * **Technology Flexibility:** Different services could potentially use different technologies in the future.
        * **Scalability:** Each service can be scaled independently based on its specific load.
    * **Negative / Trade-offs:**
        * **Increased Operational Complexity:** Requires managing a distributed system, including service discovery, an API gateway, and centralized logging/tracing.
        * **Complex Local Setup:** The developer environment (`docker-compose`) is more complex, as it must run multiple services.
        * **Network Overhead:** All inter-service communication now involves network latency and potential failures.