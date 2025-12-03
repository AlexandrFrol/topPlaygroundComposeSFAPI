# SpaceFlight News

Application for tracking spaceflight news using the [Spaceflight News API](https://spaceflightnewsapi.net/).

## Features

- View list of spaceflight articles
- View detailed article information
- View full article in-app via WebView
- Save articles to favorites
- View saved favorite articles

## Architecture

The application is built on **clean architecture** principles using the **MVVM** pattern:

- **Data Layer**: API interfaces, DTO models, Room database, repositories
- **Domain Layer**: Entities, Use Cases, Repository interfaces
- **Presentation Layer**: ViewModels, UI screens (Jetpack Compose)

## Technologies

- **Kotlin** - programming language
- **Jetpack Compose** - modern UI framework
- **Hilt** - dependency injection
- **Retrofit** - for REST API work
- **Room** - local database for storing favorites
- **Navigation Compose** - navigation between screens
- **Coil** - image loading and display
- **Coroutines & Flow** - asynchronous programming

## Project Structure

```
app/src/main/java/com/example/topplaygroundcompose/
├── data/
│   ├── local/          # Room database, DAO, entities
│   ├── remote/         # API interfaces, DTO
│   └── repository/     # Repository implementations
├── domain/
│   ├── model/          # Domain entities
│   ├── repository/     # Repository interfaces
│   └── usecase/        # Use cases
├── presentation/
│   ├── articles/       # Articles list screen
│   ├── article_detail/ # Article details screen
│   ├── article_webview/ # WebView screen for full article
│   ├── favorites/      # Favorites screen
│   ├── components/     # Reusable UI components
│   └── navigation/     # Navigation
├── di/                 # Hilt modules
└── ui/theme/           # App theme
```

## Installation and Setup

1. Clone the repository
2. Open the project in Android Studio
3. Wait for Gradle sync
4. Run the application on an emulator or device

## Requirements

- Android Studio Hedgehog or newer
- Min SDK: 26
- Target SDK: 36
- Kotlin 2.0.21

## Implementation Details

- All string resources are stored in `strings.xml`
- All color resources are stored in `colors.xml` and `Colors.kt`
- Jetpack Navigation is used for navigation
- Back button works correctly without cluttering the navigation stack
- `MainActivity` contains only navigation and serves as a container for screens
- No extra Activities in the project
- Dependencies are managed through Hilt DI

