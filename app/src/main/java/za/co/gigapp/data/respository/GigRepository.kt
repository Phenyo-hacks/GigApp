package za.co.gigapp.data.repository

import kotlinx.coroutines.flow.Flow
import za.co.gigapp.data.local.Gig
import za.co.gigapp.data.local.GigDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GigRepository @Inject constructor(
    private val gigDao: GigDao
) {
    suspend fun createGig(gig: Gig): Boolean {
        return try {
            gigDao.insertGig(gig)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getAllOpenGigs(): Flow<List<Gig>> {
        return gigDao.getAllOpenGigs()
    }

    fun getGigsByUser(userEmail: String): Flow<List<Gig>> {
        return gigDao.getGigsByUser(userEmail)
    }

    suspend fun getGigById(gigId: Int): Gig? {
        return gigDao.getGigById(gigId)
    }
}