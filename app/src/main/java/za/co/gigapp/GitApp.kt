package za.co.gigapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Main Application class annotated with @HiltAndroidApp
 * Required for Hilt dependency injection
 */
@HiltAndroidApp
class GigApp : Application()
