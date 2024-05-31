package com.malbyte.pointrecordingapp.ui.screen.home_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.malbyte.pointrecordingapp.R

@Composable
fun UpdateBottomSheet(
    name: String,
    position: String,
    fault: String,
    addPoin: Int,
    poin: Int,
    addButton: () -> Unit,
    openListFault: (visible: Boolean) -> Unit,
    openListFaultValue: Boolean,
    listFaultPoin: (addPoint: Int, fault: String) -> Unit
) {
    val listPoin = listOf(
        Fault(
            "Makan bediri",
            12
        ),
        Fault(
            "Nendang tong sampah",
            20
        )
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Row(
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier.size(
                    width = 140.dp,
                    height = 170.dp
                ),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "Nama       : $name",
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Position   : $position",
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "pelanggaran : $fault",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Poin        : $addPoin",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Light,
                )
            }
            Text(
                text = "${poin + addPoin}",
                fontSize = 35.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(15.dp))

        IconButton(
            modifier = Modifier.fillMaxWidth(),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            onClick = {
                addButton()
            }
        ) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = null,
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Buka list Pelanggaran",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 10.sp,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center
        )
        IconButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { openListFault(!openListFaultValue) }
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowDropDown,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
        if (!openListFaultValue) {
            Spacer(modifier = Modifier.height(40.dp))
        }
        AnimatedVisibility(visible = openListFaultValue) {

            LazyColumn(
                content = {

                    items(listPoin) {

                        Card(
                            modifier = Modifier
                                .fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(4.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface,
                                contentColor = MaterialTheme.colorScheme.onSurface
                            ),
                            onClick = {
                                listFaultPoin(it.poin, it.pelanggaran)
                            }
                        ) {

                            Row(
                                modifier = Modifier.padding(10.dp)
                            ) {
                                Text(
                                    text = it.pelanggaran,
                                    modifier = Modifier.weight(1f),
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "+${it.poin}",
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                })
        }
        if (openListFaultValue) {
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

data class Fault(
    val pelanggaran: String,
    val poin: Int
)