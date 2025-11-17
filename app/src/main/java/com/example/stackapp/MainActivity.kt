package com.example.stackapp

import android.util.Log
import android.app.Activity
import android.content.Context
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
import android.widget.Toast

val myStack = IntArray(3)
val newStack = IntArray(3)
var stackIndex = 0

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{StackAppUI(this, stackIndex)}


    }
}


fun pop (myStack: IntArray, context: Context)
{
    val popToast = Toast.makeText(context, "Stack is Empty!", Toast.LENGTH_SHORT)

    try {
        stackIndex--
        myStack.copyInto(newStack,0,0, stackIndex)
        newStack.copyInto(myStack)
    } catch (e: ArrayIndexOutOfBoundsException)
    {
        popToast.show();
        stackIndex = 0
    }


}

fun push (myStack: IntArray, num: Int, context: Context)
{
    val pushToast = Toast.makeText(context, "Stack is Full!", Toast.LENGTH_SHORT)
    try {
        myStack[stackIndex] = num
        stackIndex++
        Log.d("Stack Index Inside Push", stackIndex.toString())

    } catch (e: ArrayIndexOutOfBoundsException) {
        pushToast.show();
        stackIndex = 2
    }
}
// ---------- UI ----------
@Composable
fun StackAppUI(context: Context, index: Int) {
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
                    push(myStack, num, context)
                    stackDisplay = myStack.contentToString()
                    inputValue = TextFieldValue("")
                    Log.d("Current Stack", myStack.contentToString())
                    Log.d("Current index", index.toString())
                }
            }) {
                Text("Push")
            }

            Button(onClick = {
                pop(myStack, context)
                stackDisplay = myStack.contentToString()
                Log.d("Current Stack", myStack.contentToString())
                Log.d("Current index", index.toString())
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