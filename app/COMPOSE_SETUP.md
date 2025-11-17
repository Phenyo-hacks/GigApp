Jetpack Compose setup

This branch adds Jetpack Compose UI files. To build these screens, enable Compose in your app module by adding the necessary Compose and Kotlin compiler options and dependencies. Example (add to app/build.gradle.kts):

android {
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.3"
  }

  // ensure you have a compatible kotlin version in root gradle.properties, eg kotlin.code.style
}

dependencies {
  implementation("androidx.activity:activity-compose:1.7.2")
  implementation("androidx.compose.ui:ui:1.5.3")
  implementation("androidx.compose.material3:material3:1.1.1")
  implementation("androidx.compose.ui:ui-tooling-preview:1.5.3")
  debugImplementation("androidx.compose.ui:ui-tooling:1.5.3")
}