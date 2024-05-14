package com.malbyte.pointrecordingapp.ui.screen.home_screen.components

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun InsertEmployeeBottomSheet(
    insertBtn: (name: String?, position: String?, poin: Int?) -> Unit
) {
    val context = LocalContext.current

    var nameTfState by remember {
        mutableStateOf("")
    }
    var positionTfState by remember {
        mutableStateOf("")
    }
    var poinTfState by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = nameTfState,
            label = {
                Text(
                    text = "Nama",
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            onValueChange = {
                nameTfState = it
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = positionTfState,
            label = {
                Text(
                    text = "Position",
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            onValueChange = {
                positionTfState = it
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = poinTfState,
            label = {
                Text(
                    text = "Poin",
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            onValueChange = {
                poinTfState = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(10.dp))
        IconButton(
            modifier = Modifier.fillMaxWidth(),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            onClick = {

                if (nameTfState == "" || positionTfState == ""){
                    Toast.makeText(context, "Salah satu kolom belum ter-isi", Toast.LENGTH_SHORT).show()
                    return@IconButton
                }
                insertBtn(nameTfState, positionTfState, if (poinTfState == "") 0 else poinTfState.toInt())
            }
        ) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = null,
            )
        }
        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun InsertEmployeeBottomSheetPrev() {
    InsertEmployeeBottomSheet(){ name, position, poin ->  
        
    }
}