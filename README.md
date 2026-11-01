# HomeComing

Single-activity Jetpack Compose app aligned with [Google app architecture](https://developer.android.com/topic/architecture) and [Now in Android](https://github.com/android/nowinandroid) conventions.

**Full design:** [docs/ARCHITECTURE.md](docs/ARCHITECTURE.md) · **Gradle module plan:** [docs/MODULARIZATION_ROADMAP.md](docs/MODULARIZATION_ROADMAP.md)

## Architecture (summary)

```
app/
├── MainActivity.kt / HomeComingApp.kt / HomeComingApplication.kt
├── navigation/                 # NavHost + bottom bar (app-level wiring)
├── di/                         # Hilt modules
├── core/
│   ├── model/                  # Event, HomeHighlight
│   ├── domain/                 # Use cases (Flow from repositories)
│   ├── data/                   # EventRepository + in-memory impl + local source
│   ├── designsystem/theme/
│   └── navigation/             # Typed Route, destinations, NavController helpers
└── feature/{home,events,profile}/
    Route → @HiltViewModel → Screen (UDF)
```

| Layer | Responsibility |
|-------|----------------|
| **Screen** | Stateless UI: `UiState` in, events out |
| **Route** | `hiltViewModel()`, lifecycle-aware collection |
| **ViewModel** | `stateIn(WhileSubscribed(5_000))` |
| **Domain** | `Observe*UseCase` — thin wrappers over repository streams |
| **Data** | Repository is the only public data API |

Navigation: `@Serializable sealed interface Route` with `composable<Route.Home>`.

## Toolchain

AGP **9.5**, Kotlin **2.4.21**, Compose BOM **2026.09.00**, Navigation **2.10.2**, Hilt **2.60**, KSP, Baseline Profile **1.5.0**.

## Backend (Spring Boot + GraphQL)

Java **17**, Spring Boot **3.5**, **GraphQL** API (not REST for product data). Layered hexagonal layout — [docs/BACKEND.md](docs/BACKEND.md).

```powershell
docker compose up -d
$env:JAVA_HOME = 'C:\Program Files\Eclipse Adoptium\jdk-17.0.18.8-hotspot'
.\gradlew.bat :backend:bootRun
```

| URL | Purpose |
|-----|---------|
| `POST /graphql` | GraphQL endpoint |
| `http://localhost:8080/graphiql` | GraphiQL (local) |
| `/actuator/health` | Health |

Tests: `.\gradlew.bat :backend:test`

## Run (Android)

Android Studio **2026.2.2+**, JDK **17**, SDK **37**.

```powershell
$env:JAVA_HOME = 'C:\Program Files\Eclipse Adoptium\jdk-17.0.18.8-hotspot'
.\gradlew.bat :app:assembleDebug
```

Release / baseline profile:

```powershell
.\gradlew.bat :app:assembleRelease
.\gradlew.bat :app:generateReleaseBaselineProfile
```

## Conventions

- Copy in `res/values/strings.xml`
- `@Immutable` on feature `*UiState` types
- No repository service locators — Hilt only
- Release: R8 full mode, shrink resources
