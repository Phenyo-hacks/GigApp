package za.co.gigapp.ui.theme.gigs

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import za.co.gigapp.data.local.Gig
import za.co.gigapp.data.repository.GigRepository
import javax.inject.Inject

@HiltViewModel
class GigViewModel @Inject constructor(
    private val repository: GigRepository
) : ViewModel() {


    var gigMessage: String? by mutableStateOf(null)

    fun createGig(
        title: String,
        description: String,
        category: String,
        location: String,
        budget: Double,
        postedBy: String
    ) {
        viewModelScope.launch {
            val gig = Gig(
                title = title,
                description = description,
                category = category,
                location = location,
                budget = budget,
                postedBy = postedBy
            )
            val success = repository.createGig(gig)
            gigMessage = if (success) "Gig posted successfully!" else "Failed to post gig"
        }
    }

    fun getAllOpenGigs(): Flow<List<Gig>> {
        return repository.getAllOpenGigs()
    }

    fun getGigsByUser(userEmail: String): Flow<List<Gig>> {
        return repository.getGigsByUser(userEmail)
    }

    // ✅ ADD THIS METHOD:
    suspend fun getGigById(gigId: Int): Gig? {
        return repository.getGigById(gigId)
    }

    fun resetGigMessage() {
        gigMessage = null
    }
}