package com.malbyte.pointrecordingapp.ui.screen.home_screen.components
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.malbyte.pointrecordingapp.R

@Composable
fun EmployeeItem(
    employeeName: String,
    employeePosition: String,
    employeePoin: Int,
    openBottomSheet: (visible: Boolean, content: Int) -> Unit,
    delete: (visible: Boolean) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
        onClick = {
            openBottomSheet(true, 0)
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(contentAlignment = Alignment.TopEnd) {
                Box() {
                    Image(
                        painter = painterResource(
                            id = R.drawable.ic_launcher_background
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        contentScale = ContentScale.Crop,
                        contentDescription = ""
                    )
                }
                IconButton(
                    modifier = Modifier
                        .padding(10.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant, CircleShape),
                    onClick = { delete(true) }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Delete,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = employeeName,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = employeePosition,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Point: $employeePoin",
                    fontSize = 11.sp,
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(5.dp)
                        )
                        .padding(start = 10.dp, top = 5.dp, bottom = 5.dp, end = 60.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant

                )
            }
        }
    }
}

@Preview
@Composable
fun slfjvnoldnfwa() {
    EmployeeItem(
        employeeName = "Fulan bin fulan",
        employeePosition = "Programmer",
        employeePoin = 12,
        openBottomSheet = { visible, content ->

        },
        delete = {}
    )
}


