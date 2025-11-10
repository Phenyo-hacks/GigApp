package za.co.gigapp.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GigDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertGig(gig: Gig)

    @Query("SELECT * FROM gigs WHERE status = 'OPEN' ORDER BY createdAt DESC")
    fun getAllOpenGigs(): Flow<List<Gig>>

    @Query("SELECT * FROM gigs WHERE postedBy = :userEmail ORDER BY createdAt DESC")
    fun getGigsByUser(userEmail: String): Flow<List<Gig>>

    @Query("SELECT * FROM gigs WHERE id = :gigId")
    suspend fun getGigById(gigId: Int): Gig?

    @Update
    suspend fun updateGig(gig: Gig)
}