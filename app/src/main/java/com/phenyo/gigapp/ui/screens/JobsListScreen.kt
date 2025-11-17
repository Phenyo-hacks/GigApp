package com.phenyo.gigapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.phenyo.gigapp.data.Job
import com.phenyo.gigapp.ui.components.JobCard

@Composable
fun JobsListScreen(
    jobs: List<Job>,
    onPostClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Hello, Daisy!") },
            actions = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(jobs) { job ->
                JobCard(job = job)
            }
        }

        FloatingActionButton(
            onClick = onPostClick,
            modifier = Modifier
                .padding(24.dp)
                .size(64.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Post")
        }
    }
}