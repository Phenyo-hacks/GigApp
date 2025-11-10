package za.co.gigapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gigs")
data class Gig(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val category: String,
    val location: String,
    val budget: Double,
    val postedBy: String,
    val status: String = "OPEN",
    val createdAt: Long = System.currentTimeMillis()
)