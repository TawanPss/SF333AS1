package com.example.numguess

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numguess.ui.theme.NumGuessTheme
import java.text.NumberFormat
import java.util.Random

var amountInput: MutableState<String> = mutableStateOf("0")
var randomnumber = rannum()


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
    var cnt  by remember { mutableStateOf(0) }
    var amountInput by remember { mutableStateOf("") }
    var nInput by remember { mutableStateOf(0) }
    var win  by remember { mutableStateOf(0) }
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
                .fillMaxWidth(),
            value = amountInput,
            onValueChanged = { amountInput = it }

        )


        if(nInput == 0){
            Text(text = "")
        }
        else if(nInput == randomnumber){
            Text(text = "Correct!")
        }
        else if(nInput < randomnumber){
            Text(text = "Hint: It's lower!")
        }
        else{
            Text(text = "Hint: It's higher!")
        }
        Button(onClick = {
            if(win == 0){
            nInput = amountInput.toInt()
                if(nInput != randomnumber){
                    cnt  += 1
                }
        }
            else{
                win = 0
                nInput = 0
                cnt = 0
                randomnumber = rannum()
            }
        }
        ) {
            if (nInput == randomnumber){
                win = 1
                Text(stringResource(R.string.playAgain), fontSize = 30.sp)
            }
            else{
                Text(text = "send", fontSize = 30.sp)
            }
        }
        if(win != 0){
            Text(text = "Number of times before a correct guess: " + cnt.toString(),
                style = MaterialTheme.typography.overline,
                fontSize = 15.sp
            )
        }
    }
}

@Composable
fun EditNumberField(
                    modifier: Modifier = Modifier,
                    value: String,
                    onValueChanged: (String) -> Unit,
                    ) {
    TextField(
        value = value,
        onValueChange = onValueChanged,
        singleLine = true,
        label = { Text(stringResource(R.string.inputNum)) },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done,
        ),
            modifier = modifier
    )
}
private fun rannum():Int{
    val rnds = (1..1000).random()
    return rnds
}



