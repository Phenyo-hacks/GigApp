package com.phenyo.gigapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.phenyo.gigapp.ui.theme.PurpleStart
import com.phenyo.gigapp.ui.theme.PurpleEnd

@Composable
fun PostJobScreen(onPost: (/*Job payload*/)->Unit = {}) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Brush.linearGradient(listOf(PurpleStart, PurpleEnd)))
        ) {
            Text("Create New Task", color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.padding(24.dp), style = MaterialTheme.typography.headlineSmall)
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-32).dp)
            .padding(horizontal = 16.dp)
        ) {
            Card(shape = RoundedCornerShape(20.dp), modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(8.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    var title by remember { mutableStateOf("") }
                    var description by remember { mutableStateOf("") }
                    TextField(value = title, onValueChange = { title = it }, label = { Text("Title") }, modifier = Modifier.fillMaxWidth())
                    Spacer(Modifier.height(12.dp))
                    TextField(value = description, onValueChange = { description = it }, label = { Text("Description") }, modifier = Modifier.fillMaxWidth(), maxLines = 4)
                    Spacer(Modifier.height(16.dp))
                    Button(onClick = { /* validate & post */ }, modifier = Modifier.fillMaxWidth()) {
                        Text("Create Task")
                    }
                }
            }
        }
    }
}