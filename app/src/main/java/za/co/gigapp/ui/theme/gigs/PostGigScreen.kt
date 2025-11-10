package za.co.gigapp.ui.theme.gigs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostGigScreen(
    navController: NavController,
    currentUserEmail: String,
    viewModel: GigViewModel = hiltViewModel()
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var budget by remember { mutableStateOf("") }

    val categories = listOf(
        "Tutoring", "Delivery", "Design", "Writing", "Photography",
        "Cleaning", "Tech Support", "Translation", "Music", "Other"
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Post a Gig") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Title Input
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Gig Title") },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("e.g., Need website design") }
            )

            // Description Input
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                placeholder = { Text("Describe what you need...") },
                singleLine = false,
                maxLines = 4
            )

            // Category Dropdown
            var expanded by remember { mutableStateOf(false) }
            val focusRequester = remember { FocusRequester() }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true }
            ) {
                OutlinedTextField(
                    value = category,
                    onValueChange = {},
                    label = { Text("Category") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                    readOnly = true,
                    enabled = false,
                    placeholder = { Text("Select a category") }
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    categories.forEach { cat ->
                        DropdownMenuItem(
                            text = { Text(cat) },
                            onClick = {
                                category = cat
                                expanded = false
                            }
                        )
                    }
                }
            }

            // Location Input
            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                label = { Text("Location") },
                leadingIcon = {
                    Icon(Icons.Default.LocationOn, contentDescription = "Location")
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("e.g., Johannesburg, Remote") }
            )

            // Budget Input - FIXED KEYBOARD OPTIONS
            OutlinedTextField(
                value = budget,
                onValueChange = { budget = it },
                label = { Text("Budget (R)") },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("e.g., 500") }

            )

            // Post Button
            Button(
                onClick = {
                    if (title.isBlank() || description.isBlank() || category.isBlank() || location.isBlank() || budget.isBlank()) {
                        viewModel.gigMessage = "Please fill all fields"
                    } else {
                        viewModel.createGig(
                            title = title,
                            description = description,
                            category = category,
                            location = location,
                            budget = budget.toDoubleOrNull() ?: 0.0,
                            postedBy = currentUserEmail
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                enabled = title.isNotBlank() && description.isNotBlank() && category.isNotBlank() && location.isNotBlank() && budget.isNotBlank()
            ) {
                Text("Post Gig", style = MaterialTheme.typography.titleMedium)
            }

            // Show success/error messages
            viewModel.gigMessage?.let { message ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = if (message.contains("success", ignoreCase = true)) {
                            MaterialTheme.colorScheme.primaryContainer
                        } else {
                            MaterialTheme.colorScheme.errorContainer
                        }
                    )
                ) {
                    Text(
                        text = message,
                        modifier = Modifier.padding(16.dp),
                        color = if (message.contains("success", ignoreCase = true)) {
                            MaterialTheme.colorScheme.onPrimaryContainer
                        } else {
                            MaterialTheme.colorScheme.onErrorContainer
                        }
                    )
                }

                // Auto-clear message after 3 seconds
                LaunchedEffect(message) {
                    kotlinx.coroutines.delay(3000)
                    viewModel.resetGigMessage()
                }
            }
        }
    }
}