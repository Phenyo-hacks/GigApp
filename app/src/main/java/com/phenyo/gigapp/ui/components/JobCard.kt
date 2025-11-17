package com.phenyo.gigapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Work
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.phenyo.gigapp.ui.theme.PurpleStart
import com.phenyo.gigapp.ui.theme.PurpleEnd
import com.phenyo.gigapp.ui.theme.TextMuted
import com.phenyo.gigapp.data.Job

@Composable
fun JobCard(job: Job, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(22.dp),
        modifier = modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(
                        brush = Brush.linearGradient(listOf(PurpleStart, PurpleEnd)),
                        shape = RoundedCornerShape(14.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Work,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(job.title, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurface)
                Spacer(modifier = Modifier.height(6.dp))
                Text(job.location + " • " + "${job.pay}", fontSize = 13.sp, color = TextMuted)
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(job.createdAt?.toDate()?.toString() ?: "", color = TextMuted, fontSize = 12.sp)
            }
        }
    }
}