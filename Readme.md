# Incident Analysis Platform

## Overview

This project is a **backend-focused incident analysis platform** that simulates how internal reliability tooling (e.g., PagerDuty, Datadog, Sentry) detects and analyzes production incidents.

The system ingests high-volume service events (logs, alerts, deploy signals), **correlates them deterministically into incidents**, and layers **AI-assisted analysis** on top to help engineers understand what might be happening - without letting AI affect correctness.

---

## High-Level Architecture

```
              ┌──────────────┐
              │   Services   │
              │ (Producers)  │
              └──────┬───────┘
                     │
                     │  HTTP Events
                     ▼
              ┌──────────────┐
              │ Event API    │
              │ (/v1/events) │
              └──────┬───────┘
                     │
                     │ Persist (short-lived)
                     ▼
              ┌──────────────┐
              │ Events Table │
              │ (Ephemeral)  │
              └──────┬───────┘
                     │
        Scheduled     │   Time-window queries
        Correlation   ▼
              ┌──────────────────────┐
              │ Correlation Service  │
              │ - Group by service   │
              │ - Apply thresholds   │
              │ - Deduplicate        │
              └──────┬───────────────┘
                     │
                     │ Create / Update
                     ▼
              ┌──────────────┐
              │ Incidents    │
              │ (State)      │
              └──────┬───────┘
                     │
         Scheduled    │   Read-only input
         AI Analysis  ▼
              ┌──────────────────────┐
              │ AI Worker             │
              │ (Non-blocking)        │
              └──────┬───────────────┘
                     │
                     │ Persist insight
                     ▼
              ┌──────────────┐
              │ AI Insights  │
              │ (Derived)    │
              └──────┬───────┘
                     │
                     │
                     ▼
              ┌──────────────────────┐
              │ Read APIs             │
              │ - /v1/incidents       │
              │ - /v1/incidents/{id}  │
              │   /analysis            │
              └──────────────────────┘
```

---

## Core Design Principles

### 1. Deterministic First, AI Second

Incident detection is based on **deterministic rules** (time windows, thresholds, deduplication). AI is used only to **interpret and summarize** incidents after they exist.

AI never:

* Creates incidents
* Changes incident state
* Affects correctness

---

### 2. Ephemeral Signals vs Durable State

The system distinguishes between:

* **Ephemeral signals**: raw events/logs used only for short-term correlation
* **Durable state**: incidents and AI summaries that represent long-term operational knowledge

Older events are expected to be evicted or ignored to prevent unbounded database growth.

---

### 3. Asynchronous, Non-Blocking Workflows

Both correlation and AI analysis run as **background scheduled jobs**:

* Event ingestion is fast and fire-and-forget
* Correlation runs periodically and is idempotent
* AI analysis is isolated so failures do not impact incident detection

---

## What This Project Demonstrates

* Event-driven backend design
* Time-window-based correlation logic
* Background job orchestration
* Clear separation of concerns (MVC + domain services)
* Safe integration of AI into production-style systems

---

## What This Project Intentionally Does NOT Do

* Replace a logging system (ELK, Loki, etc.)
* Use AI for primary detection or clustering
* Store raw logs indefinitely
* Provide a UI

These tradeoffs are intentional and documented.

---

## Tech Stack

* Java 17
* Spring Boot
* Spring Data JPA
* Scheduled background workers
* (Pluggable) AI analyzer interface

---

## Future Improvements (Out of Scope)

* Idempotent ingestion
* Auto-resolution of incidents
* Metrics and benchmarking
* Security (API keys, RBAC)
* Streaming ingestion (Kafka)
