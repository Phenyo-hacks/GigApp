package za.co.gigapp.ui.theme.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import za.co.gigapp.data.local.User
import za.co.gigapp.data.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    // ✅ FIXED: Use mutableStateOf for Compose observation
    var authMessage: String? by mutableStateOf(null)
        private set

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    // ✅ ADD THIS: Current user state
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    fun register(username: String, email: String, password: String) {
        viewModelScope.launch {
            val success = repository.registerUser(User(username = username, email = email, password = password))
            authMessage = if (success) "Registration successful!" else "User already exists!"
        }
    }

    fun resetAuthMessage() {
        authMessage = null
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val user = repository.loginUser(email, password)
            if (user != null) {
                // ✅ FIXED: Set the current user
                _currentUser.value = user
                authMessage = "Welcome ${user.username}!"
                _isLoggedIn.value = true
            } else {
                authMessage = "Invalid credentials!"
                _isLoggedIn.value = false
            }
        }
    }

    // ✅ ADD THIS: Logout function
    fun logout() {
        viewModelScope.launch {
            _currentUser.value = null
            _isLoggedIn.value = false
            authMessage = "Logged out successfully"
        }
    }
}