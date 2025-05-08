package com.alex.taskhive.ui.screens.manager

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.alex.taskhive.viewmodel.ScheduleViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssignScheduleScreen(
    navController: NavController,
    scheduleViewModel: ScheduleViewModel,
    workerId: String
) {
    val daysOfWeek = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    // Track selected work days
    val schedule = remember { mutableStateMapOf<String, Boolean>().apply {
        daysOfWeek.forEach { put(it, false) }
    }}

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Assign Schedule") })
        }
    ) { padding ->
        Column(modifier = Modifier
            .padding(padding)
            .padding(16.dp)
        ) {
            Text("Select work days for the worker: $workerId") // You can use workerId here
            Spacer(modifier = Modifier.height(16.dp))

            daysOfWeek.forEach { day ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .toggleable(
                            value = schedule[day] == true,
                            onValueChange = { checked -> schedule[day] = checked }
                        )
                ) {
                    Checkbox(
                        checked = schedule[day] == true,
                        onCheckedChange = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(day)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = {
                // Save the schedule by passing workerId
                scheduleViewModel.saveSchedule(workerId, schedule)
                navController.popBackStack()
            }) {
                Text("Save Schedule")
            }
        }
    }
}
