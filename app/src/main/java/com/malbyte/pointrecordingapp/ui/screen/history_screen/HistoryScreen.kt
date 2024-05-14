package com.malbyte.pointrecordingapp.ui.screen.history_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.local.model.PoinHistory
import com.malbyte.pointrecordingapp.ui.screen.history_screen.components.HistoryPoinItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun HistoryScreen(
    historyViewModel: HistoryViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val getAllHistory by historyViewModel.getHistory.collectAsStateWithLifecycle()

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
    ) {

        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {

            LazyColumn(content = {
                items(getAllHistory) {

                    HistoryPoinItem(
                        employeeName = it.employeeName,
                        fault = it.fault,
                        poinIn = it.poinIn,
                        poinOut = it.poinOut,
                        date = it.createdAt
                    ) {
                        historyViewModel.deleteHistory(
                            PoinHistory(
                                id = it.id,
                                employeeName = it.employeeName,
                                fault = it.fault,
                                poinIn = it.poinIn,
                                poinOut = it.poinOut,
                                recorderName = "Malik",
                                createdAt = "now()"
                            )
                        )
                    }
                }
            })
        }
    }
}