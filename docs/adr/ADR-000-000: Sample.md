# ADR-001-001: Choice of Password Hashing Algorithm

* **Status:** Accepted
* **Date:** 2025-08-15
* **Context:** We need a secure method to store user passwords. The algorithm must be resistant to modern brute-force attacks using GPUs.
* **Decision:** We will use **Argon2id** as implemented by Spring Security.
* **Consequences:** This provides state-of-the-art security against both cracking and side-channel attacks. We will fall back to Bcrypt if Argon2id library support becomes an issue.


Title: A short, descriptive name for the decision. e.g., "ADR-001: Choice of Database for Identity Platform."

Status: Accepted, Deprecated, Superseded. This shows the decision's current relevance.

Context: This is the most important part. Briefly describe the problem you're solving, the technical constraints, and the business drivers. What led to this decision needing to be made?

Decision: State the chosen solution clearly and unambiguously. e.g., "We will use PostgreSQL as the primary transactional database for all services."

Consequences: Briefly list the results of the decision. This includes the positive outcomes, the negative trade-offs, and any new work or risks that are introduced. This demonstrates you've considered the full picture.