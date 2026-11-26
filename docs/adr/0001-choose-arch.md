# ADR-0001: Choose the system architecture

- **Related:** Issue #1, `docs/requirements.md`

## Context

I need to choose an architecture for a full-stack backpacking PWA with a one-man team scope.

## Options considered

| Option | Pros | Cons |
| --- | --- | --- |
| **A. Client-server, modular monolith, layered backend** | One deploy; simple to run, test and debug; clear structure | Doesn't scale parts independently (not needed at this size) |
| B. Client-server, microservices (gear, loadout and coverage services) | Independent deploys and scaling | Several deploys, network calls between services, too much work for one developer |
| C. Frontend only, using Supabase directly (backend-as-a-service) | Fewest moving parts | Business logic (coverage, weight totals) lives in the browser or in SQL, so it's harder to unit test |


## Decision

We use **option A**: a client-server architecture with a single-deployable **modular monolith** backend.

- **Modules by feature:** `weather/`, `cities/`, and `shared/` (logging, error handling, health checks, ...)
- **Layers within each module:** routes → services → data access (NWS client, cache, city dataset). Each layer only calls the layer below it.
- **Dependency rule at the NWS boundary:** the weather service depends on an NWS client interface, not on HTTP calls directly, so tests can substitute a fake client
- **Frontend:** a component-based single-page app that calls only our backend, never NWS directly

The specific languages and frameworks are recorded separately in ADR-0002.

## Consequences

**Positive**

- One app to run locally, test and deploy, which fits the 1-day, $0 constraints
- Layers and feature modules give clear separation of concerns, so each part can be tested on its own
- The NWS client interface makes failure cases (US-3) easy to test with a fake
- An in-memory cache works, because there is one long-running process

**Negative**

- The in-memory cache is lost on restart and isn't shared if we ever run more than one instance (acceptable at this scale)
- On a free host, the app may sleep when idle, making the first request slow (C-5)
- If the app grows, a module could later be split into its own service; the module boundaries keep that option open