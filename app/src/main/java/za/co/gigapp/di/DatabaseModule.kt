package za.co.gigapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import za.co.gigapp.data.local.AppDatabase
import za.co.gigapp.data.local.GigDao
import za.co.gigapp.data.local.UserDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "gig_app_db"
        )
            .fallbackToDestructiveMigration() // This will wipe DB on schema change
            .build()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    // ✅ NOW THIS WILL WORK - GigDao exists!
    @Provides
    fun provideGigDao(database: AppDatabase): GigDao {
        return database.gigDao()
    }
}