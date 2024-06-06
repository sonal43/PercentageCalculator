package com.example.mupointercalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mupointercalculator.ui.theme.MuPointerCalculatorTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MuPointerCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Calculator()
                }
            }
        }
    }
}

@Composable
fun Calculator(){

    var inputValue by remember { mutableStateOf("") }
    var inputType by remember { mutableStateOf("CGPA") }
    var outputType by remember { mutableStateOf("Percentage") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    var result by remember { mutableStateOf(0.00) }

    val customTextStyle = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontSize = 25.sp,
        color = Color.DarkGray
    )

    fun calculate(){
        val inputDouble = inputValue.toDoubleOrNull() ?: 0.00
        if (inputType=="CGPA" && outputType=="Percentage"){
            result = 7.25 * inputDouble + 11
        }
        else if (inputType=="Percentage" && outputType=="CGPA") {
            result = (inputDouble - 11) / 7.25
        }
        else {
            result = 0.00
        }
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Percentage Converter", style = customTextStyle)
        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue = it
            calculate()
        })
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box {
                Button(onClick = { iExpanded = true }) {
                    Text(inputType)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Percentage") },
                        onClick = {
                            iExpanded = false
                            inputType = "Percentage"
                            calculate()
                        })
                    DropdownMenuItem(
                        text = { Text("CGPA") },
                        onClick = {
                            iExpanded = false
                            inputType = "CGPA"
                            calculate()
                        })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = { oExpanded = true }) {
                    Text(outputType)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Percentage") },
                        onClick = {
                            oExpanded = false
                            outputType = "Percentage"
                            calculate()
                        })
                    DropdownMenuItem(
                        text = { Text("CGPA") },
                        onClick = {
                            oExpanded = false
                            outputType = "CGPA"
                            calculate()
                        })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: %.2f $outputType ".format(result))
    }
}