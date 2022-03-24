package com.example.numberguessinggame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            NumberGuessing()
        }
    }
}

var rngNumber = Random.nextInt(1, 1000)

@Composable
fun NumberGuessing() {
    val guessValue = remember { mutableStateOf("") }
    val count = remember { mutableStateOf(0) }
    val adaptText = remember { mutableStateOf("") }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .offset(y=25.dp)
                .padding(15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Attempted : "+ count.value,
                fontSize = 25.sp,
                textAlign = TextAlign.Left,

            )

            Button(

                onClick = {
                    count.value=0
                    adaptText.value=""
                    rngNumber = Random.nextInt(1, 1000)}
            ){
                Text(text="Reset", fontSize = 25.sp)
            }
        }
        Text(
            text = "Number Guessing Game",
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .offset(y=55.dp)
                .padding(50.dp)
                .fillMaxWidth()

            )
        val focusManager = LocalFocusManager.current
        TextField(
            value = guessValue.value,
            onValueChange = { guessValue.value = it },
            label = { Text("Insert the number (1-1000)")},
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            modifier = Modifier
                .padding(45.dp)
                .fillMaxWidth()

        )

        Button(

            modifier = Modifier
                .offset(x=0.dp,y=0.dp)
                .padding(8.dp)
                ,

            onClick = {
                if (guessValue.value.toInt() < rngNumber){
                    count.value += 1
                    adaptText.value = "GUESS HIGHER!"
                }
                else if (guessValue.value.toInt() > rngNumber){
                    count.value += 1
                    adaptText.value = "GUESS LOWER!"
                }
                else {
                    count.value += 1
                    adaptText.value = "CORRECT YOU WIN!"

                }

            }

        ){
            Text(text="Confirm", fontSize = 25.sp)

        }

        Text(
            text = adaptText.value,
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .offset(y=8.dp)
                .padding(50.dp)
                .fillMaxWidth()

        )
    }





}

