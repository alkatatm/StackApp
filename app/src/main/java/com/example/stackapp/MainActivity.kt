package com.example.stackapp

import android.util.Log
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kotlin.system.exitProcess
import android.widget.Button
val myStack = IntArray(3)
val newStack = IntArray(3)
var stackIndex = 0
class MainActivity : ComponentActivity() {

    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Initial", myStack.contentToString())
        push(myStack, 3)
        Log.d("Push1Stack", myStack.contentToString())
        push(myStack, 7)
        Log.d("Push2Stack", myStack.contentToString())
        pop(myStack)
        Log.d("Pop1Stack", newStack.contentToString())
        push(myStack, 5)
        Log.d("Push3Stack", myStack.contentToString())
        push(myStack, 2)
        Log.d("Push4Stack", myStack.contentToString())
        pop(myStack)
        Log.d("Pop2Stack", newStack.contentToString())

        //setContentView(R.layout.activity_main)

        //button = findViewById(R.id.idBtnCloseApplication)

        button.setOnClickListener {
            //finishAffinity()

            exitProcess(0)

        }
    }
}


fun pop (myStack: IntArray)
{
    myStack.copyInto(newStack,0,0, stackIndex - 1)
    newStack.copyInto(myStack)
    if(stackIndex >= 0)
        stackIndex--
    else
        stackIndex = 2
}

fun push (myStack: IntArray, num: Int)
{
    myStack[stackIndex] = num
    if(stackIndex <= 2)
        stackIndex++
    else
        stackIndex = 0

}
// ---------- UI ----------
@Composable
fun StackAppUI() {
    val context = LocalContext.current
    var inputValue by remember { mutableStateOf(TextFieldValue("")) }
    var stackDisplay by remember { mutableStateOf(myStack.contentToString()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Stack: $stackDisplay",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = inputValue,
            onValueChange = { inputValue = it },
            label = { Text("Enter number") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = {
                val num = inputValue.text.toIntOrNull()
                if (num != null) {
                    push(myStack, num)
                    stackDisplay = myStack.contentToString()
                    inputValue = TextFieldValue("")
                }
            }) {
                Text("Push")
            }

            Button(onClick = {
                pop(myStack)
                stackDisplay = myStack.contentToString()
            }) {
                Text("Pop")
            }

            Button(onClick = {
                (context as? Activity)?.finish()
            }) {
                Text("Quit")
            }
        }
    }
}