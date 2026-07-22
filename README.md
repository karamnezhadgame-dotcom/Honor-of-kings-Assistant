Honor of Kings Assistant

王者荣耀助手 · 东方幻想


An immersive Android companion app for Honor of Kings (王者荣耀) players, blending ancient Chinese aesthetics with real-time game data, hero insights, and interactive build simulations.

---

✨ Features

🏯 Hero Encyclopedia (英雄录)

· Browse all heroes with stunning splash art
· View detailed skills, lore, skins, and difficulty ratings
· Live search by name or title
· Offline‑first caching (Room + Retrofit)

📊 Meta Insights (大神观)

· Global win, pick, and ban rates
· Player lookup by summoner name
· Match history and recent performance
· Coming soon: real‑time recommendation engine

📜 World Lore (峡谷志)

· Explore the rich universe of King’s Canyon
· Region‑based chapters with immersive artwork
· Hero associations and story snippets
· Traditional scroll‑style reading experience

⚔️ Build Simulator (装备模拟器)

· Drag‑and‑drop item crafting
· 6 equipment slots mimicking in‑game UI
· Live stat preview (planned)
· Share builds with friends

🔥 Live Tracker (实时追踪)

· Accessibility‑based overlay reads enemy cooldowns, gold difference, objective timers
· Works on top of the game (requires manual permission grant)
· Fully draggable and customizable
· Privacy‑first: no data is stored or transmitted

---

🧱 Architecture

This app follows clean MVVM with Repository pattern and offline‑first design.

```
┌─────────────────────────────────────────────────────────────┐
│                           UI Layer                          │
│  Activities / Fragments / ViewModels / LiveData / Binding   │
└─────────────────────────────┬───────────────────────────────┘
                              │
┌─────────────────────────────▼───────────────────────────────┐
│                        Repository Layer                     │
│  Data sources: Room (local) + Retrofit (mock/real API)      │
│  Caching & sync logic, fallback handling                    │
└─────────────────────────────┬───────────────────────────────┘
                              │
┌─────────────────────────────▼───────────────────────────────┐
│                    Data Sources (Local/Remote)              │
│  Room Database (Heroes, Lore, Builds)                       │
│  Retrofit + Mock Interceptor (simulated backend)            │
└─────────────────────────────────────────────────────────────┘
```

· Navigation: Android Navigation component with bottom tabs
· Persistence: Room with JSON converters for nested objects
· Networking: Retrofit + OkHttp with a mock interceptor (ready for real API swap)
· Image Loading: Glide with placeholder fallbacks
· Background: AccessibilityService + foreground overlay (WindowManager)

---

🚀 Getting Started

Prerequisites

· Android Studio Hedgehog (2023.1.1) or later
· Android SDK 34 (API 34)
· Kotlin 1.9.20

Setup

1. Clone the repository
   ```bash
   git clone https://github.com/karamnezhadgame-dotcom/honor-assistant.git
   cd honor-assistant
   ```
2. Open in Android Studio
   · Select File > Open and choose the project root.
3. Build the project
   · Wait for Gradle sync to finish.
   · Run Build > Make Project (Ctrl+F9).
4. Run on emulator or device
   · Select a target (API 24+ recommended).
   · Click Run (Shift+F10).

Enabling Accessibility (Live Tracker)

1. Install the app and launch it.
2. Go to Settings > Accessibility > Honor Assistant Tracker (or navigate via the toggle in the app).
3. Enable the service.
4. The overlay will appear when you open Honor of Kings.

---

📁 Project Structure

```
app/src/main/
├── java/com/honorassistant/app/
│   ├── data/                 # Models, DB, Network, Repository, DataSources
│   │   ├── database/         # Room entities, DAO, Converters
│   │   ├── models/           # Hero, Lore, Item, PlayerStats
│   │   ├── network/          # Retrofit, ApiService, MockInterceptor
│   │   ├── repository/       # HeroRepository, ProInsightsRepository
│   │   └── datasource/       # Static data providers (mock data)
│   ├── service/              # AccessibilityService & OverlayService
│   ├── ui/                   # Fragments, ViewModels, Adapters
│   │   ├── home/             # Dashboard
│   │   ├── hero/             # Hero list & detail
│   │   ├── insights/         # Stats placeholder
│   │   ├── lore/             # Lore list & detail
│   │   └── buildsimulator/   # Drag‑and‑drop item builder
│   └── utils/                # Helpers, extensions
├── res/                      # Layouts, drawables, values, menu, navigation, xml
│   ├── drawable/             # Icons, backgrounds, selectors
│   ├── layout/               # All fragment/activity layouts
│   ├── menu/                 # Bottom navigation menu
│   ├── navigation/           # Nav graph
│   ├── values/               # Colors, themes, strings
│   └── xml/                  # Accessibility config
└── AndroidManifest.xml
```

---

🎨 Design Language

· Theme: Eastern Fantasy – inspired by ancient Chinese art.
· Palette: Gold (#C9A96E) for royalty, Ink Grey (#2C2C2C) for depth, Parchment (#F4F1EA) for backgrounds, and traditional hues like Canghuang and Sha Lv.
· Typography: Serif fonts for titles (calligraphic feel), sans‑serif for readability.
· UI Elements: Scroll‑like headers, seal‑style buttons, cloud‑pattern dividers, and curved card designs.

---

🧪 Testing & Continuous Improvement

· Unit Tests (planned): JUnit + Mockito for ViewModels and Repositories.
· UI Tests (planned): Espresso for navigation and search.
· Performance: Profiling with Android Studio; lazy loading in RecyclerViews.
· Self‑correction mechanism: The app gracefully falls back to cached data on network failures and logs errors for later analysis.

---

🤝 Contributing

We welcome contributions! To get started:

1. Fork the repository.
2. Create a feature branch (git checkout -b feature/amazing-feature).
3. Commit your changes (git commit -m 'Add some amazing feature').
4. Push to the branch (git push origin feature/amazing-feature).
5. Open a Pull Request.

Please read our Code of Conduct and follow the existing coding style (Kotlin, 4‑space indentation).

---

📄 License

Distributed under the MIT License. See LICENSE for more information.

---

📬 Contact

· Author: karamnezhadgame-dotcom
· Project Link: https://github.com/karamnezhadgame-dotcom/honor-assistant

---

🙏 Acknowledgments

· Honor of Kings (王者荣耀) – for the inspiring universe.
· The open‑source community – for the amazing libraries (Room, Retrofit, Glide, etc.).
· All beta testers who helped shape the experience.

---

“十步杀一人，千里不留行。” – 李白
