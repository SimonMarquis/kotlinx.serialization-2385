# kotlinx.serialization issue [#2385](https://github.com/Kotlin/kotlinx.serialization/issues/2385)


### ✅ **Without** minification

- Build the project without minification
  ```bash
  ./gradlew installDebug -PisMinifyEnabled=false
  ```
- Launch the app and click the screen to trigger the network request
- You should see a toast with the network result (nb of repositories for user octocat)

### ❌ **With** minification
  ```bash
  ./gradlew installDebug -PisMinifyEnabled=true
  ```
- Launch the app and click the screen to trigger the network request
- The app will crash this the following stacktrace:
  ```
  Caused by: kotlinx.serialization.SerializationException: Serializer for class 'Any' is not found.
  Please ensure that class is marked as '@Serializable' and that the serialization compiler plugin is applied.
  ```

### ✅ **With** minification and custom proguard rule
  ```bash
  ./gradlew installDebug -PisMinifyEnabled=true -PproguardRule=true
  ```
- Launch the app and click the screen to trigger the network request
- You should see a toast with the network result (nb of repositories for user octocat)
