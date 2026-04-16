# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Running the project

All commands run from the project root:
```
Enterprise Data Loader API/Enterprise Data Loader API/
```

```bash
# Run
./mvnw spring-boot:run

# Build (skip tests)
./mvnw package -DskipTests

# Run tests
./mvnw test

# Run a single test class
./mvnw test -Dtest=TimeUtilsTest
```

On Windows use `mvnw.cmd` instead of `./mvnw`.

No authentication required — all routes are public (`SecurityConfig` permits all).

## Architecture

Spring Boot 3 / Java 21 microservice. No database — all data is loaded from an Excel file into heap memory at startup via `@PostConstruct` and served from there. Response times are sub-10ms.

**Startup flow:** `BaseFileService.loadBaseFile()` → reads `src/main/resources/data/masivo_tiendas.xlsx` → parses rows into `List<BaseRow>` → groups by `tiendaPadre` into `Map<String, List<BaseRow>>` → held in memory for the lifetime of the process.

**Two entry points:**
- `GET /` — Thymeleaf dashboard (`dashboard.html`). Passes the set of `tiendaPadre` names to the view. The JS fetches `/api/tienda/{nombre}` to load schedule data dynamically.
- `GET /api/tienda/{nombre}` — returns `Map<String, List<BaseRow>>` (grouped by `type`: turbo, turbo_express, etc.) for the requested store.

## Key design details

- **Column mapping is flexible** — `mapColumns()` in `BaseFileService` normalizes header names (lowercase, no spaces, no accents) so variations like `"Store Id"` and `"store_id"` both resolve correctly.
- **Time parsing** — `TimeUtils.fromCell()` handles both numeric Excel time fractions (e.g. `0.5` = 12:00) and text strings (`"HH:mm"`, `"HH:mm:ss"`, `"H:mm"`). Add new formats there if the Excel source changes.
- **`HomeController`** is in the root package (`com.axel.masivo_tiendas`) instead of `.controller` — intentional legacy placement, don't move without updating Spring component scan.
- **No persistence layer** — changes made via the dashboard (schedule edits, CSV export) are client-side only. Nothing writes back to the Excel or any DB.
- **`signals` table** — referenced in the README but does not exist; it's reserved for a future feature.
