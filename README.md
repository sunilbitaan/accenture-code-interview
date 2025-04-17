#Accenture-Code-Challenge

This is an Android application developed using Kotlin. The application is built with Android Studio Koala | 2024.1.1 and uses Gradle for build and dependency management.

## Description
This demo application to used to search employer and show them in list.
- User can search which is responsive search, as soon as user enter text result will be shown in milliseconds.
- User can click any employer from the list and navigate to Employer Detail screen.

## Setup

1. Clone the repository to your local machine.
2. Ensure you have java 11 / jsk 11 installed
3. Open the project in Android Studio.
4. Sync the project with Gradle files.
5. Run the application on an emulator or physical device.

## Dependencies

The project uses the following dependencies:

- AndroidX Core KTX
- AndroidX AppCompat
- Material Design
- AndroidX Material3
- AndroidX UI Tooling Preview
- AndroidX Activity Compose
- Kotlin Coroutines
- Koin for dependency injection
- WorkManager for background tasks
- AndroidX LiveData
- Gson for JSON processing
- Retrofit for network requests
- OkHttp Logging Interceptor
- AndroidX Hilt Work
- AndroidX Lifecycle Runtime Compose


## Application use cases:
- Here I have used compose for UI design.
- Retrofit used for api calling.

## Architecture used:
- MVVM with Clean Architecture
- Koin framework for dependency Injection

## API Reference

#### Get all employers

 ```http
   GET https://cba.kooijmans.nl/CBAEmployerservice.svc/rest/employers?filter=Achmea&maxRows=100
 ```

| Parameter       | Type     | Description |
 |:----------------|:---------|:------------|
| `filter`        | `string` | "Ac"        |
| `maxRows`       | `int`    | 100         |



## Authors

- [@sunilbitaan](https://github.com/sunilbitaan/accenture-code-interview.git)