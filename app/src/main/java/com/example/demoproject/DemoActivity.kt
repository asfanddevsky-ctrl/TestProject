package com.example.demoproject

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.demoproject.ui.theme.DemoProjectTheme

class DemoActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DemoProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    displayViewss(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }


    @Composable
    fun displayViewss(modifier: Modifier) {
        var input1 by remember { mutableStateOf("") }
        var input2 by remember { mutableStateOf("") }
        var result by remember { mutableStateOf(0) }



        LazyColumn(modifier = modifier) {

            item {

                //Addition Start
                Text(text = "Addition")
                TextField(
                    value = input1,
                    onValueChange = {
                        input1 = it
                    },
                    label = { Text("First Number") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))

                TextField(value = input2, onValueChange = {
                    input2 = it
                })

                Button(onClick = {

                    val num1 = input1.toInt()
                    val num2 = input2.toInt()

                    result = num1 + num2


                }) {
                    Text("Add")
                }

                Text(text = "Result is :$result")
                //Addition Ends


                //Subtraction Start
                Text(text = "Subtraction")
                TextField(
                    value = input1,
                    onValueChange = {
                        input1 = it
                    },
                    label = { Text("First Number") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))

                TextField(value = input2, onValueChange = {
                    input2 = it
                })

                Button(onClick = {

                    val num1 = input1.toInt()
                    val num2 = input2.toInt()

                    result = num1 - num2


                }) {
                    Text("Subtract")
                }

                Text(text = "Result is :$result")
                //Subtraction Ends

                //Multiplication Start
                Text(text = "Multiplication")
                TextField(
                    value = input1,
                    onValueChange = {
                        input1 = it
                    },
                    label = { Text("First Number") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))

                TextField(value = input2, onValueChange = {
                    input2 = it
                })

                Button(onClick = {

                    val num1 = input1.toInt()
                    val num2 = input2.toInt()

                    result = num1 * num2


                }) {
                    Text("Multiply")
                }

                Text(text = "Result is :$result")
                //Multiplication Ends

                //Division Start
                Text(text = "Division")
                TextField(
                    value = input1,
                    onValueChange = {
                        input1 = it
                    },
                    label = { Text("First Number") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))

                TextField(value = input2, onValueChange = {
                    input2 = it
                })

                Button(onClick = {

                    val num1 = input1.toInt()
                    val num2 = input2.toInt()

                    result = num1 / num2


                }) {
                    Text("Divide")
                }

                Text(text = "Result is :$result")
                //Division Ends
            }
        }



    }





//    android compose make a counter display which updates on button click - simple compose code







    @Composable
    fun dispayView(modifier: Modifier) {
        var resultCount by remember {
            mutableStateOf(0)
        }
        Column(modifier = modifier) {
            Text(
                text = "Clicked Count is : "
            )

            Button(onClick = {
                Log.d("logsdata", "Clicked........")
            }) {
                Text(text = "Click Here")
            }
        }
    }

}


//                    LazyColumn(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
//                        items(listdata) {
//                            Text(
//                                text = "Valye is : " + it
//                            )
//                        }
//                    }



