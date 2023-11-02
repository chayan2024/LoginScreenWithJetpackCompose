package com.withjetpack.loginscreenwithjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.withjetpack.loginscreenwithjetpackcompose.ui.theme.LoginScreenWithJetpackComposeTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
                    LoginScreen()
                }
            }
        }
    }


    @Composable
    fun LoginScreen() {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            // Background with a custom curved shape
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Blue),
            )
            {
                CurvedBackgroundShape()
            }

            // Login Form
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Your login form components go here
                Text(
                    text = "Login",
                    fontSize = 24.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))

                BasicTextField(
                    value = "Username",
                    onValueChange = { /* Handle text changes */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(8.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .background(Color.White)
                )

                Spacer(modifier = Modifier.height(16.dp))

                BasicTextField(
                    value = "Password",
                    onValueChange = { /* Handle text changes */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(8.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .background(Color.White)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { /* Handle login button click */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                ) {
                    Text(text = "Login")
                }
            }
        }
    }

    @Composable
    fun CurvedBackgroundShape() {
        val curveHeight = 150.dp
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

    @Preview
    @Composable
    fun LoginScreenPreview() {
        LoginScreen()
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
