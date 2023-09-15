package com.example.numguess

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numguess.ui.theme.NumGuessTheme
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType

var amountInput: MutableState<String> = mutableStateOf("0")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumGuessTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    Column {
                        TopAppBar(title = {
                            Row {
                                Text(
                                    "Guessing Number Game",
                                    color = Color.White,
                                    fontSize = 30.sp,
                                    lineHeight = 30.sp,
                                )
                            }
                        })
                        Fillbox(message = "Try to guess the number I'm thinking of from 1-1000")
                    }
                }
            }
        }
    }
}

@Composable
fun Fillbox(message: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(top = 100.dp)
    ) {
        Text(
            text = message,
            fontSize = 30.sp,
            lineHeight = 50.sp,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(top = 100.dp)
        )
        EditNumberField(
            modifier = Modifier
                .padding(top = 50.dp, bottom = 50.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth()
        )
        Button(onClick = { /*TODO*/ }
        ) {
            Text(stringResource(R.string.playAgain), fontSize = 30.sp)
        }
    }
}

@Composable
fun EditNumberField(modifier: Modifier = Modifier) {
    var amountInput by remember { mutableStateOf("") }
    TextField(
        value = amountInput,
        onValueChange = { amountInput = it },
        singleLine = true,
        label = { Text(stringResource(R.string.inputNum)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier
    )
}


