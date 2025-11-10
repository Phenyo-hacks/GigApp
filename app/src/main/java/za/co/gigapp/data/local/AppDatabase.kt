package za.co.gigapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [User::class, Gig::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun gigDao(): GigDao
}