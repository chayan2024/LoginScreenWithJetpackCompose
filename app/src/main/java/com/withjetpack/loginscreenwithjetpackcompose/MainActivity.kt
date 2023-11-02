package com.withjetpack.loginscreenwithjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.withjetpack.loginscreenwithjetpackcompose.ui.theme.LoginScreenWithJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreenWithJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CurvedBackgroundShape()
                    LoginForm()
                }
            }
        }
    }


    @Composable
    fun LoginForm() {

        var username by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }
        var isPasswordVisible by remember {
            mutableStateOf(false)
        }
        val isFormValid by derivedStateOf {
            username.isNotBlank() && password.length >= 7
        }
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Card(
                Modifier
                    .weight(2f)
                    .padding(10.dp,60.dp,10.dp,60.dp),
                shape = RoundedCornerShape(32.dp)
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    Text(text = "Welcome to jetpack Compose Login !!", fontWeight = FontWeight.Bold, fontSize = 32.sp)
                    Column(
                        Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.weight(0.5f))
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = username,
                            onValueChange = { username = it },
                            label = { Text(text = "Username") },
                            singleLine = true,
                            trailingIcon = {
                                if (username.isNotBlank())
                                    IconButton(onClick = { username = "" }) {
                                        Icon(
                                            imageVector = Icons.Filled.Clear,
                                            contentDescription = ""
                                        )
                                    }
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = password,
                            onValueChange = { password = it },
                            label = { Text(text = "Password") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            ),
                            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            trailingIcon = {
                                PasswordVisibilityIcon(
                                    isPasswordVisible = isPasswordVisible,
                                    onToggleClick = { isPasswordVisible = !isPasswordVisible }
                                )
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {},
                            enabled = isFormValid,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Red, // Set the default background color
                                contentColor = Color.White // Set the text color
                            )
                        ) {
                            Text(text = "Log In")
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            TextButton(onClick = {}) {
                                Text(text = "Sign Up")
                            }
                            TextButton(onClick = { }) {
                                Text(text = "Forgot Password?")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PasswordVisibilityIcon(
    isPasswordVisible: Boolean,
    onToggleClick: () -> Unit
) {
    val icon: ImageVector =
        if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff

    IconButton(
        onClick = onToggleClick
    ) {
        Icon(
            imageVector = icon,
            contentDescription = if (isPasswordVisible) "Hide Password" else "Show Password"
        )
    }
}

@Composable
fun CurvedBackgroundShape() {
    val curveHeight = 100.dp
    val curveOffset = 20.dp

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize(),
            onDraw = {
                val path = Path().apply {
                    moveTo(0f, 0f)
                    lineTo(size.width, 0f)
                    lineTo(size.width, size.height - curveHeight.toPx())
                    quadraticBezierTo(
                        size.width / 2f,
                        size.height + curveOffset.toPx(),
                        0f,
                        size.height - curveHeight.toPx()
                    )
                    close()
                }
                drawPath(path, color = Color.Blue)
            }
        )
    }
}
