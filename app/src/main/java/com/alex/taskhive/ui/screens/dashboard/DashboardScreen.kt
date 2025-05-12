package com.alex.taskhive.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alex.taskhive.navigation.ROUT_CHAT
import com.alex.taskhive.ui.theme.Black
import com.alex.taskhive.ui.theme.newOrange
import com.alex.taskhive.viewmodel.DashboardViewModel

@Composable
fun DashboardScreen(navController: NavController, viewModel: DashboardViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val shiftState by viewModel.shift.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
            .padding(24.dp)
    ) {
        if (shiftState == null) {
            CircularProgressIndicator(color = newOrange, modifier = Modifier.align(Alignment.Center))
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Your Shift",
                    fontSize = 24.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    colors = CardDefaults.cardColors(containerColor = newOrange),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = shiftState!!.day, color = Black, fontSize = 18.sp)
                        Text(text = shiftState!!.time, color = Black, fontSize = 22.sp)
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))

                Button(
                    onClick = { navController.navigate("chat_screen") },
                    colors = ButtonDefaults.buttonColors(containerColor = newOrange),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text("Go to Chat", color = Black, fontSize = 18.sp)
                }
            }
        }
    }
}
