package com.malbyte.pointrecordingapp.ui.screen.history_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HistoryPoinItem(
    employeeName: String,
    fault: String,
    poinIn: Int,
    poinOut: Int,
    date: String?,
    delete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = employeeName,
                fontSize = 20.sp
            )
            Text(
                text = date!!,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "pelanggaran : $fault",
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Poin        : $poinIn",
                        fontWeight = FontWeight.Light,
                    )
                }
                Text(
                    text = poinOut.toString(),
                    fontSize = 35.sp
                )
            }
            HorizontalDivider()
            Spacer(modifier = Modifier.height(10.dp))
            IconButton(onClick = { delete() }) {
                Icon(imageVector = Icons.Rounded.Delete, contentDescription = null)
            }
        }
    }
}

