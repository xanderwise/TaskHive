package com.alex.taskhive.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alex.taskhive.repository.UserRepository
import com.alex.taskhive.ui.screens.auth.LoginScreen
import com.alex.taskhive.ui.screens.auth.RegisterScreen
import com.alex.taskhive.ui.screens.manager.AssignScheduleScreen
import com.alex.taskhive.ui.screens.manager.TaskScheduleScreen
import com.alex.taskhive.ui.screens.splash.SplashScreen
import com.alex.taskhive.viewmodel.AuthViewModel
import com.alex.taskhive.viewmodel.ManagerViewModel
import com.alex.taskhive.viewmodel.ScheduleViewModel
import com.yourapp.data.UserDatabase


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH,
) {
    val applicationContext = LocalContext.current

    // Initialize database, repository, and viewmodel properly
    val appDatabase = UserDatabase.getDatabase(applicationContext)
    val authRepository = UserRepository(appDatabase.userDao())
    val authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(authRepository)
    )

    // Manager ViewModel initialization
    val managerViewModel: ManagerViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }

        composable(ROUT_REGISTER) {
            RegisterScreen(navController, authViewModel)
        }

        composable(ROUT_LOGIN) {
            LoginScreen(navController, authViewModel)
        }

        composable(ROUT_TASK) {
            TaskScheduleScreen(navController, managerViewModel) // Pass the managerViewModel here
        }

        composable("${ROUT_ASSIGN}/{workerId}") { backStackEntry ->
            val workerId = backStackEntry.arguments?.getString("workerId")
            if (workerId != null) {
                val scheduleViewModel: ScheduleViewModel = viewModel()
                AssignScheduleScreen(
                    navController = navController,
                    scheduleViewModel = scheduleViewModel,
                    workerId = workerId
                )
            }
        }



    }
}
