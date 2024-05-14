package com.malbyte.pointrecordingapp.ui.screen.auth.register_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {

    val context = LocalContext.current

    var userNameState by remember {
        mutableStateOf("")
    }
    var positionState by remember {
        mutableStateOf("")
    }

    var emailState by remember {
        mutableStateOf("")
    }

    var passState by remember {
        mutableStateOf("")
    }

    val registerState by registerViewModel.registerState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Sign Up") },
                navigationIcon = {
                    IconButton(onClick = { navigator.navigateUp() }) {
                        Icon(imageVector = Icons.Rounded.ArrowBackIosNew, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = userNameState,
                onValueChange = {
                    userNameState = it
                },
                placeholder = {
                    Text(
                        text = "Name",
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = positionState,
                onValueChange = {
                    positionState = it
                },
                placeholder = {
                    Text(
                        text = "Position",
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = emailState,
                onValueChange = {
                    emailState = it
                },
                placeholder = {
                    Text(
                        text = "Email",
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)

            )
            Spacer(modifier = Modifier.height(15.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = passState,
                onValueChange = {
                    passState = it
                },
                placeholder = {
                    Text(
                        text = "Password",
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)

            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                onClick = {
                    registerViewModel.signUp(
                        email = emailState,
                        password = passState,
                        username = userNameState,
                        position = positionState,
                    )
                }
            ) {

                Text(text = "Register")
            }
        }

        registerState.DisplayResult(
            onLoading = { /*TODO*/ },
            onSuccess = {
                Toast.makeText(context, "Sign Up Success", Toast.LENGTH_SHORT).show()
            }
        ) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            Log.d("limit cennah", it)
        }
    }
}