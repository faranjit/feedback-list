# Android Clean Architecture Boilerplate

This project is created to demonstrate how to implement basic clean architecture and developed with 100% Kotlin. There are 3 layers for each feature.
1. Presentation: UI components(activity, fragments, viewmodel etc.)
2. Data: Access to remote data sources.
3. Domain: Bridge between presentation and data layers.

In this project there is no local data source but if you have, you need to put in to the data layer along with remote data source.

I used a dummy response to simulate service calls instead of getting from service since I do not have a service. That response is placed in assets folder named **apidemo.json**.
I created an interceptor. This interceptor returns the content of **apidemo.json** as json for each request.
When creating the application, that file is read in app module and sent to **MockServer** just once since it is provided by **single**.

The hard part of the coroutines is testing. In this project you can find simple test cases that shows how to test viewModel or another suspend functions.

## Libraries and tools used

* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html)
* Android Support Libraries
* [Koin](https://github.com/InsertKoinIO/koin)
* [Glide](https://github.com/bumptech/glide)
* [Retrofit](http://square.github.io/retrofit/)
* [OkHttp](http://square.github.io/okhttp/)
* [Coroutines](https://developer.android.com/kotlin/coroutines)
* [Kotlinx.Serialization](https://github.com/Kotlin/kotlinx.serialization)
* [Google Maps](https://developers.google.com/maps/documentation/android-sdk/overview)

## Requirements

* JDK 1.8
* [Android SDK](https://developer.android.com/studio/index.html)
* Min Android Version: API 16
* Target Android Version: API 30
* Latest Android SDK Tools and build tools.
* Android Studio: I recommend this Canary version: Android Studio Arctic Fox | 2020.3.1 Canary 7

## Features
* Home
* Filter
* Detail

### Home
Enter point of the application. Basically it lists all of the feedbacks. Its view model calls the service via **GetFeedbacks** use case.
In the action bar there are 3 options:
  1. Filter: Starts FilterActivity.
  2. Starred: Lists just starred feedbacks.
  3. All: Lists all feedbacks.
  
### Filter
It lists filtered feedbacks with rating, label, country and city.
Since I don't have a backend I created filter data, contains labels list, countries list and cities list, parsing the that dummy response.

### Detail
It displays some informations about the selected feedback. It starts when you click a feedback listed in Home or Filter.
On top of the activity there is map fragment diplays location of the feedback. To use this map you have to create ***secret.properties*** in root folder of the application
and define a value named **GOOGLE_MAPS_API_KEY**.

## What is Next?
Some new features can be added for example a new map fragment showing clustered feedback locations in the map. You can select one navigate through the details.
Also it will be good to have sort attributes(time, rating...) or search with comment, timing etc.
Maybe I can add image gallery that shows up images of the feedback.
