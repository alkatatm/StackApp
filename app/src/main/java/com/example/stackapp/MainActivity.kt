package com.example.stackapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import  android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.stackapp.ui.theme.StackAppTheme

val myStack = IntArray(3)
val newStack = IntArray(3)
var stackIndex = 0
class MainActivity : ComponentActivity() {
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
