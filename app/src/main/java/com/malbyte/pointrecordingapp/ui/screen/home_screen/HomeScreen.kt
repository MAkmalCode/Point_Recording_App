package com.malbyte.pointrecordingapp.ui.screen.home_screen

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.History
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.local.LocalUser
import com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.local.model.PoinHistory
import com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.remote.model.Employee
import com.malbyte.pointrecordingapp.ui.screen.destinations.AccountScreenDestination
import com.malbyte.pointrecordingapp.ui.screen.destinations.HistoryScreenDestination
import com.malbyte.pointrecordingapp.ui.screen.home_screen.components.EmployeeItem
import com.malbyte.pointrecordingapp.ui.screen.home_screen.components.InsertEmployeeBottomSheet
import com.malbyte.pointrecordingapp.ui.screen.home_screen.components.UpdateBottomSheet
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.time.LocalDateTime
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val context = LocalContext.current
    val listEmployee = homeViewModel.listEmployeeState.collectAsStateWithLifecycle()
    val listAccount = homeViewModel.listAccountState.collectAsStateWithLifecycle()
    val insertState by homeViewModel.insertState.collectAsStateWithLifecycle()
    val updateState by homeViewModel.updateState.collectAsStateWithLifecycle()

    val searchText by homeViewModel.searchText.collectAsState()
    val isSearching by homeViewModel.isSearching.collectAsState()

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    var showAllertDialog by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        homeViewModel.connectToRealTime()
    }

    var openListFault by remember {
        mutableStateOf(false)
    }
    var bsContent by remember {
        mutableStateOf(0)
    }

    var nameState by remember {
        mutableStateOf("....")
    }
    var positionState by remember {
        mutableStateOf("....")
    }
    var poinState by remember {
        mutableStateOf(0)
    }

    var addPoinState by remember {
        mutableStateOf(0)
    }
    var faultState by remember {
        mutableStateOf("....")
    }
    var idState by remember {
        mutableStateOf("....")
    }

    Scaffold(
        topBar = {

            TopAppBar(
                title = {
                    Text(text = LocalUser.username ?: "Homu")
                },
                actions = {
                    IconButton(onClick = { navigator.navigate(HistoryScreenDestination) }) {
                        Icon(
                            imageVector = Icons.Rounded.History,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }

                    IconButton(onClick = { navigator.navigate(AccountScreenDestination) }) {
                        Icon(
                            imageVector = Icons.Rounded.AccountCircle,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            )
        },

        floatingActionButton = {

            FloatingActionButton(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                onClick = {
                    bsContent = 1
                    showBottomSheet = true
                }
            ) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            listAccount.value.DisplayResult(
                onLoading = {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                },
                onSuccess = { listEmployee ->

                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        SearchBar(
                            query = searchText,
                            onQueryChange = { homeViewModel.onSearchTextChange(it) },
                            onSearch = { homeViewModel.onSearchTextChange(it) },
                            active = isSearching,
                            onActiveChange = { homeViewModel.onToogleSearch() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            placeholder = {
                                Text(
                                    text = "Search",
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        ) {

                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                content = {

                                    items(listEmployee.filter {
                                        it.username!!.contains(
                                            searchText,
                                            true
                                        )
                                    }) { account ->
                                        EmployeeItem(
                                            employeeName = account.username!!,
                                            employeePosition = account.position!!,
                                            employeePoin = account.poin!!,
                                            delete = { visible ->
                                                idState = account.id
                                                showAllertDialog = visible
                                            },
                                            openBottomSheet = { visible, content ->
                                                showBottomSheet = visible
                                                nameState = account.username
                                                positionState = account.position
                                                poinState = account.poin
                                                idState = account.id
                                                bsContent = content
                                            }
                                        )
                                    }
                                }
                            )
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            content = {

                                items(listEmployee) { account ->
                                    EmployeeItem(
                                        employeeName = account.username!!,
                                        employeePosition = account.position!!,
                                        employeePoin = account.poin!!,
                                        delete = { visible ->
                                            idState = account.id
                                            showAllertDialog = visible
                                        },
                                        openBottomSheet = { visible, content ->
                                            showBottomSheet = visible
                                            nameState = account.username
                                            positionState = account.position
                                            poinState = account.poin
                                            idState = account.id
                                            bsContent = content
                                        }
                                    )
                                }
                            }
                        )
                    }

                    if (showAllertDialog) {
                        AlertDialog(
                            icon = {
                                Icon(
                                    imageVector = Icons.Rounded.Delete,
                                    contentDescription = null
                                )
                            },
                            title = { Text(text = "Hapus item ini?") },
                            text = {
                                Text(
                                    text = "Item yang akan di hapus tidak dapat dipulihkan kembali",
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                            },
                            onDismissRequest = { showAllertDialog = false },
                            confirmButton = {
                                TextButton(
                                    onClick = {
                                        homeViewModel.deleteAccount(idState)
                                        showAllertDialog = false
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

                    if (showBottomSheet) {
                        ModalBottomSheet(
                            onDismissRequest = {
                                showBottomSheet = false
                            },
                            sheetState = sheetState
                        ) {

                            if (bsContent == 0) {
                                UpdateBottomSheet(
                                    name = nameState,
                                    position = positionState,
                                    fault = faultState,
                                    addPoin = addPoinState,
                                    poin = poinState,
                                    addButton = {
                                        if (addPoinState == 0){
                                            Toast.makeText(context, "Pelanggaran belum dipilih", Toast.LENGTH_SHORT).show()
                                            return@UpdateBottomSheet
                                        }
                                        homeViewModel.updatePoinAccount(
                                            idState,
                                            poinState + addPoinState
                                        )
                                        val history =
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                                PoinHistory(
                                                    id = null,
                                                    employeeName = nameState,
                                                    fault = faultState,
                                                    poinIn = addPoinState,
                                                    poinOut = poinState + addPoinState,
                                                    recorderName = "Malik",
                                                    createdAt = LocalDateTime.now().toString()
                                                )
                                            } else {
                                                TODO("VERSION.SDK_INT < O")
                                            }
                                        homeViewModel.insertHistory(history)
                                        addPoinState = 0
                                        faultState = "...."
                                        showBottomSheet = false
                                    },
                                    openListFault = {

                                        openListFault = it
                                    },
                                    openListFaultValue = openListFault,
                                    listFaultPoin = { addPoint, fault ->

                                        addPoinState = addPoint
                                        faultState = fault
                                    }
                                )
                            } else {
                                InsertEmployeeBottomSheet(
                                    insertBtn = { name, position, poin ->

                                        val employee = Employee(
                                            id = UUID.randomUUID().toString(),
                                            name = name,
                                            position = position,
                                            poin = poin,
                                            createdAt = "now()"
                                        )
                                        homeViewModel.insertEmployee(employee)

                                        showBottomSheet = false
                                    }
                                )
                            }
                        }
                    }
                }) { errorText ->

                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = errorText)
                }
            }

            insertState.DisplayResult(
                onLoading = {

                },
                onSuccess = {

                    Log.d("Cape asli", "$it")
                }
            ) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose { homeViewModel.leaveRealtimeChannel() }
    }
}





