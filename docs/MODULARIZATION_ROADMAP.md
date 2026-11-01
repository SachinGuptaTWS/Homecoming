# Modularization roadmap

HomeComing currently uses **one `:app` module** with **package boundaries** that match [Now in Android](https://github.com/android/nowinandroid) module types. Extract Gradle modules when parallel builds, ownership, or test isolation justify the cost—not before.

## Phase 0 — Done

- Feature folders (`home`, `events`, `profile`) with Route / Screen / ViewModel / UiState.
- `core.model`, `core.domain`, `core.data`, `core.designsystem`, `core.navigation`.
- Hilt, typed navigation, repository boundary.

## Phase 1 — JVM cores

| Module | Contents | Depends on |
|--------|----------|------------|
| `:core:model` | `Event`, `HomeHighlight` | — |
| `:core:domain` | Use cases | `:core:model`, repository interfaces |

Use `org.jetbrains.kotlin.jvm` (no Android) for pure Kotlin modules and faster unit tests.

## Phase 2 — Shared Android core

| Module | Contents |
|--------|----------|
| `:core:designsystem` | Theme, typography, colors, shared composables |
| `:core:navigation` | `Route`, `TopLevelDestination`, `NavControllerExt` |
| `:core:data` | Repository impl, Room/DataStore/network when added |

`:app` keeps `MainActivity`, `HomeComingApp`, and `HomeComingNavHost` until Phase 3.

## Phase 3 — Feature api / impl

For each feature (e.g. home):

- `:feature:home:api` — navigation destination type / public contract.
- `:feature:home:impl` — Route, Screen, ViewModel, strings scoped to feature if desired.

Cross-feature navigation: depend only on `*:api` modules ([Navigation 3 modularization](https://developer.android.com/guide/navigation/navigation-3/modularize)).

## Phase 4 — Build logic (optional)

Add `build-logic/convention` (composite build) for shared `AndroidApplicationConventionPlugin`, Compose, Hilt, and static analysis—same pattern as NIA.

## Extraction checklist (per module)

1. Create module + `build.gradle.kts` with minimal plugins.
2. Move sources; fix packages if needed.
3. Set `api` vs `implementation` dependencies explicitly.
4. Run `./gradlew :app:assembleDebug` and unit tests.
5. Add a one-line README in the module describing its role (NIA style).

## Anti-patterns to avoid

- Feature `impl` → feature `impl` dependencies (use `api` + navigation keys).
- `core` → `feature` or `app` dependencies.
- God `:core:common` module; prefer focused `core:*` libraries ([modularization patterns](https://developer.android.com/topic/modularization/patterns)).
