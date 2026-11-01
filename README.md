# HomeComing

Single-activity Compose app structured like a production Google sample: **typed navigation**, **feature modules**, **repository boundary**, **stateless UI**.

## Architecture

```
app/
├── MainActivity.kt          # Edge-to-edge shell only
├── HomeComingApp.kt           # Theme + root nav
├── core/
│   ├── data/                  # EventRepository (+ in-memory impl)
│   ├── designsystem/theme/    # Material 3 Expressive tokens
│   └── navigation/            # @Serializable Route, NavHost, top-level bar
└── feature/
    ├── home/     Route → ViewModel → Screen
    ├── events/
    └── profile/
```

| Layer | Responsibility |
|-------|----------------|
| **Route** | Wires `ViewModel`, passes callbacks (no UI) |
| **Screen** | Pure composable: `UiState` in, events out |
| **ViewModel** | `stateIn(WhileSubscribed(5_000))` — no manual collectors |
| **Repository** | Single source for schedule + home highlight |

Navigation uses **type-safe routes** (`composable<Route.Home>`) with Kotlin Serialization, not string routes.

## Toolchain

AGP **9.5**, Kotlin **2.4.21**, Compose BOM **2026.09.00**, Navigation **2.10.2**, Baseline Profile **1.5.0**.

## Run

Open in Android Studio **2026.2.2+**, sync, run debug.

```powershell
.\gradlew.bat :app:assembleRelease
```

Baseline profiles (release speed):

```powershell
.\gradlew.bat :app:generateReleaseBaselineProfile
```

## Conventions

- User-visible copy in `res/values/strings.xml`
- `@Immutable` UI state; schedule sorted once at seed
- No `material-icons-extended`; no duplicate nav logic (`navigateToTopLevel`)
- Release: R8 full mode, shrink resources, strip debug logs
