package com.malbyte.pointrecordingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.local.LocalUser
import com.malbyte.pointrecordingapp.ui.screen.NavGraphs
import com.malbyte.pointrecordingapp.ui.screen.destinations.HomeScreenDestination
import com.malbyte.pointrecordingapp.ui.screen.destinations.LoginScreenDestination
import com.malbyte.pointrecordingapp.ui.theme.CustomTheme
import com.malbyte.pointrecordingapp.ui.theme.PointRecordingAppTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DestinationsNavHost(
                        navGraph = NavGraphs.root,
                        startRoute = if (!LocalUser.loginStatus) LoginScreenDestination else HomeScreenDestination
                    )
                }
            }
        }
    }
}
