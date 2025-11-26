package com.example.stackapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
<<<<<<< Updated upstream
import androidx.compose.ui.tooling.preview.Preview
import com.example.stackapp.ui.theme.StackAppTheme
=======
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import android.widget.Toast
import androidx.collection.emptyLongSet

val myStack = IntArray(3)
var stackIndex = 0
>>>>>>> Stashed changes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
<<<<<<< Updated upstream
        enableEdgeToEdge()
        setContent {
            StackAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
=======

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

fun getStacktoString(): String{
    val s = StringBuilder("")
    if (stackIndex >0) {
        for (i in 0..stackIndex - 1)
            s.append(myStack[i].toString() + " ")
    }
    else {
        s.append("stack is empty")
    }
    return s.toString();
}
// ---------- UI ----------

@Composable
fun StackAppUI(context: Context) {
    val context = LocalContext.current
    var inputValue by remember { mutableStateOf(TextFieldValue("")) }
    var stackDisplay by remember { mutableStateOf(myStack.contentToString()) }

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
                    Log.d("built", getStacktoString())
                }
            }) {
                Text("Push")
            }

            Button(onClick = {
                pop(context)
                stackDisplay = myStack.contentToString()
                Log.d("built", getStacktoString())
            }) {
                Text("Pop")
            }

            Button(onClick = {
                (context as? Activity)?.finish()
            }) {
                Text("Quit")
>>>>>>> Stashed changes
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StackAppTheme {
        Greeting("Android")
    }
}