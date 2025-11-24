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
import android.widget.Toast

val myStack = IntArray(3)
val newStack = IntArray(3)
var stackIndex = 0

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{StackAppUI(this)}


    }
}


fun pop (context: Context)
{
    val popToast = Toast.makeText(context, "Stack is Empty!", Toast.LENGTH_SHORT)

    try {
        stackIndex--
        myStack[stackIndex] = 0
    } catch (e: ArrayIndexOutOfBoundsException)
    {
        popToast.show();
    }

}

fun push (num: Int, context: Context)
{
    val pushToast = Toast.makeText(context, "Stack is Full!", Toast.LENGTH_SHORT)
    try {
        if (stackIndex < 0)
            stackIndex = 0
        myStack[stackIndex] = num
        if (stackIndex <= 2)
            stackIndex++
    } catch (e: ArrayIndexOutOfBoundsException) {
        pushToast.show();
    }
}
// ---------- UI ----------

@Composable
fun StackAppUI(context: Context) {
    val context = LocalContext.current
    var inputValue by remember { mutableStateOf(TextFieldValue("")) }
    var stackDisplay by remember { mutableStateOf(newStack.contentToString()) }

    fun showError(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }


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
            onValueChange = { newValue ->
                val text = newValue.text

                if (text.length <= 1 && text.all { it.isDigit() }) {
                    inputValue = newValue
                } else {
                    showError("Input is only 1 digit")
                }

            },
            label = { Text("Enter number") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = {
                val num = inputValue.text.toIntOrNull()
                if (num != null) {
                    push(num, context)
                    stackDisplay = myStack.contentToString()
                    inputValue = TextFieldValue("")
                }
            }) {
                Text("Push")
            }

            Button(onClick = {
                pop(context)
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