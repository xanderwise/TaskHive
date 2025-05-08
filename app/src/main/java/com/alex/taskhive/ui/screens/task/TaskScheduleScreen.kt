package com.alex.taskhive.ui.screens.manager

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alex.taskhive.model.User
import com.alex.taskhive.navigation.ROUT_ASSIGN
import com.alex.taskhive.navigation.ROUT_CHAT
import com.alex.taskhive.ui.theme.Black
import com.alex.taskhive.ui.theme.newOrange
import com.alex.taskhive.viewmodel.ManagerViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScheduleScreen(
    navController: NavController,
    viewModel: ManagerViewModel
) {
    val workers by viewModel.workers.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadRegisteredWorkers()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Task Scheduling", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Black)
            )
        },
        containerColor = Black
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (workers.isEmpty()) {
                Text(
                    text = "No registered workers yet.",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else {
                LazyColumn {
                    items(workers) { worker ->
                        WorkerItem(worker = worker) {
                            navController.navigate("${ROUT_ASSIGN}/${worker.id}")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate(ROUT_CHAT) },
                colors = ButtonDefaults.buttonColors(containerColor = newOrange),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Go to Chat", color = Black)
            }
        }
    }
}

@Composable
fun WorkerItem(worker: User, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${worker.name}", color = Color.White)
            Text(text = "Email/Phone: ${worker.emailOrPhone}", color = Color.White)
        }
    }
}
