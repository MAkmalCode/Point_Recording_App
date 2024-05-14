package com.malbyte.pointrecordingapp.ui.screen.auth.login_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.malbyte.pointrecordingapp.R
import com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.local.LocalUser
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.utils.emailChecker
import com.malbyte.pointrecordingapp.ui.screen.destinations.HomeScreenDestination
import com.malbyte.pointrecordingapp.ui.screen.destinations.LoginScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo

@Destination(start = true)
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {

    val context = LocalContext.current

    var emailState by remember {
        mutableStateOf("")
    }
    var isEmailError by remember {
        mutableStateOf(false)
    }
    var passState by remember {
        mutableStateOf("")
    }

    val loginState by loginViewModel.loginState.collectAsStateWithLifecycle()

    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.login_pana),
                contentDescription = "https://storyset.com/online"
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {

                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 34.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Login",
                        fontSize = 28.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = emailState,
                        onValueChange = {
                            emailState = it
                            isEmailError = it.emailChecker()
                        },
                        placeholder = {
                            Text(
                                text = "Email",
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        leadingIcon = {
                            Icon(imageVector = Icons.Rounded.Email, contentDescription = null)
                        },
                        isError = isEmailError,
                        supportingText = { Text(text = if (isEmailError) "Email tidak valid" else "") },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurface,
                            focusedLeadingIconColor = MaterialTheme.colorScheme.onSurface,
                            errorContainerColor = MaterialTheme.colorScheme.errorContainer,
                            errorTextColor = MaterialTheme.colorScheme.onErrorContainer
                        )
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
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = PasswordVisualTransformation(),
                        leadingIcon = {
                            Icon(imageVector = Icons.Rounded.Lock, contentDescription = null)
                        },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurface,
                            focusedLeadingIconColor = MaterialTheme.colorScheme.onSurface,
                        )
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        onClick = {
                            loginViewModel.signIn(emailState, passState)
                        }
                    ) {

                        Text(text = "Login")
                    }
                }
            }

            loginState.DisplayResult(
                onLoading = { /*TODO*/ },
                onSuccess = {
                    Toast.makeText(context, "Sign In Success", Toast.LENGTH_SHORT).show()
                    navigator.navigate(HomeScreenDestination) {
                        popUpTo(LoginScreenDestination) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                    LocalUser.loginStatus = true
                }
            ) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                Log.d("Error cennah", it)
            }
        }
    }
}