package com.malbyte.pointrecordingapp.ui.screen.account_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.malbyte.pointrecordingapp.R
import com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.local.LocalUser
import com.malbyte.pointrecordingapp.ui.screen.destinations.AccountScreenDestination
import com.malbyte.pointrecordingapp.ui.screen.destinations.HomeScreenDestination
import com.malbyte.pointrecordingapp.ui.screen.destinations.LoginScreenDestination
import com.malbyte.pointrecordingapp.ui.screen.destinations.RegisterScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun AccountScreen(
    accountViewModel: AccountViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val context = LocalContext.current

    val updateState by accountViewModel.updateState.collectAsStateWithLifecycle()

    var editAccountName by remember {
        mutableStateOf(false)
    }
    var nameTF by remember {
        mutableStateOf("")
    }

    var showAllertDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Account") },
                navigationIcon = {
                    IconButton(onClick = { navigator.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBackIosNew,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(15.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            ) {

                Column(
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Text(
                        text = "Detail Information",
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Nama        : ${LocalUser.username}",
                            fontSize = 16.sp,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            imageVector = if (editAccountName) Icons.Rounded.Cancel else Icons.Rounded.Edit,
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                editAccountName = !editAccountName
                            }
                        )
                    }
                    AnimatedVisibility(visible = editAccountName) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            OutlinedTextField(
                                value = nameTF,
                                onValueChange = { nameTF = it },
                                placeholder = {
                                    Text(
                                        text = "Name",
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                },
                                colors = TextFieldDefaults.colors(
                                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                                    unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurface,
                                    focusedLeadingIconColor = MaterialTheme.colorScheme.onSurface,
                                )
                            )
                            IconButton(onClick = {
                                try {
                                    accountViewModel.updateAccount(
                                        id = LocalUser.id!!,
                                        username = nameTF
                                    )
                                    editAccountName = false
                                } catch (e: Exception) {
                                    Toast.makeText(
                                        context,
                                        "Cek kembali kolom pengisian",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }) {
                                Icon(imageVector = Icons.Rounded.Check, contentDescription = null)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Position        : ${LocalUser.position}",
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(7.dp))
                    Text(
                        text = "Poin            : ${LocalUser.poin}",
                        fontSize = 16.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .weight(0.5f)
                            .clickable {
                                showAllertDialog = true
                            }
                            .background(MaterialTheme.colorScheme.errorContainer)
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Logout,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onErrorContainer
                        )
                        Text(
                            text = "Log Out",
                            color = MaterialTheme.colorScheme.onErrorContainer
                        )
                    }
                    Row(
                        modifier = Modifier
                            .weight(0.5f)
                            .clickable {
                                navigator.navigate(RegisterScreenDestination)
                            }
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .padding(10.dp)
                            ,
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Add,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "Add new Account",
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        if (showAllertDialog) {
            AlertDialog(
                icon = {
                    Icon(
                        imageVector = Icons.Rounded.Logout,
                        contentDescription = null
                    )
                },
                title = { Text(text = "Logout") },
                text = {
                    Text(
                        text = "Apakah anda yakin ingin logout akun ini?",
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                onDismissRequest = { showAllertDialog = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            LocalUser.loginStatus = false
                            navigator.navigate(LoginScreenDestination) {
                                popUpTo(AccountScreenDestination) {
                                    inclusive = true
                                }
                                popUpTo(HomeScreenDestination){
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                        }) {
                        Text(text = "Confirm")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showAllertDialog = false
                        }) {
                        Text(text = "Cancel")
                    }
                }
            )
        }

        updateState.DisplayResult(
            onLoading = { /*TODO*/ },
            onSuccess = {
                Toast.makeText(context, "Berhasil update", Toast.LENGTH_SHORT).show()
                LocalUser.username = nameTF
            }
        ) {
            Toast.makeText(context, "Gagal update", Toast.LENGTH_SHORT).show()
            Log.d("Error bjir", it)
        }
    }
}