package com.alex.taskhive.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.alex.taskhive.navigation.ROUT_REGISTER
import com.alex.taskhive.ui.theme.Black
import com.alex.taskhive.ui.theme.newOrange
import com.alex.taskhive.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: AuthViewModel = viewModel()
) {
    var emailOrPhone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    // Observe the login and error states from ViewModel
    val isLoading by remember { derivedStateOf { viewModel.loginSuccess == false && viewModel.errorMessage == null } }
    val errorMessage by remember { derivedStateOf { viewModel.errorMessage } }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = "Have an Account..?" +
                        "Login Then..",
                color = newOrange,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))


            // Email or Phone input field
            OutlinedTextField(
                value = emailOrPhone,
                onValueChange = { emailOrPhone = it },
                label = { Text(text = "Email or Phone", color = Color.White) },
                visualTransformation = VisualTransformation.None, // No visual transformations
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .border(
                        width = 1.dp,
                        color = if (emailOrPhone.isNotEmpty()) newOrange else Color.Gray, // Border color
                        shape = MaterialTheme.shapes.small
                    ),
                textStyle = LocalTextStyle.current.copy(color = Color.White) // White text
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password input field
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", color = Color.White) },
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    Text(
                        text = if (isPasswordVisible) "Hide" else "Show",
                        color = newOrange,
                        modifier = Modifier.clickable { isPasswordVisible = !isPasswordVisible } // Toggle password visibility
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .border(
                        width = 1.dp,
                        color = if (password.isNotEmpty()) newOrange else Color.Gray, // Border color
                        shape = MaterialTheme.shapes.small
                    ),
                textStyle = LocalTextStyle.current.copy(color = Color.White) // White text
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Login Button
            Button(
                onClick = {
                    if (emailOrPhone.isNotEmpty() && password.isNotEmpty()) {
                        // Call loginUser in the ViewModel when the user presses login
                        viewModel.loginUser(emailOrPhone, password)
                    } else {
                        viewModel.errorMessage = "Please enter both email/phone and password"
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = newOrange // Button background color
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                } else {
                    Text(text = "Login", color = Color.White)
                }
            }

            // Error Message
            if (errorMessage != null) {
                Text(text = errorMessage ?: "", color = Color.Red)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Navigate to Register Screen
            Text(
                text = "Don't have an account? Register",
                color = newOrange,
                modifier = Modifier.clickable {
                    navController.navigate(ROUT_REGISTER) // Navigate to Register Screen
                }
            )
        }
    }
}
