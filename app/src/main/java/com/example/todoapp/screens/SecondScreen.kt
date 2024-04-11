package com.example.todoapp.screens

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.text.style.BackgroundColorSpan
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SecondScreen(navController: NavController){
//    Scaffold(
//        topBar = {
//            Row (
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.Start,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Icon(
//                    imageVector = Icons.Filled.ArrowBack,
//                    contentDescription = "Back",
//                    modifier = Modifier
//                        .padding(16.dp)
//                        .clickable {
//                            navController.popBackStack()
//                        }
//                )
//                Text(
//                    text = "Add Task",
//                    fontSize = 22.sp
//                )
//            }
//        }
//    ) {
//        AddTask(navController)
//    }
}

@Composable
fun AddTask(){ //add this "navController: NavController"
    //'add task' topbar
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    var endDate by rememberSaveable { mutableStateOf(Date().time) }

    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 80.dp, end = 16.dp),
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
        )

        val maxChar = 50

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 20.dp, end = 16.dp),
            value = description,
            onValueChange = {
                if (it.length <= maxChar) description = it
            },
            textStyle = TextStyle.Default.copy(fontSize = 16.sp, lineHeight = 24.sp),
            supportingText = {
                Text(
                    text = "${description.length} / $maxChar",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                )
            },
            label = { Text("Description") }
        )

        Spacer(modifier = Modifier.height(11.dp))

        Box (
            modifier = Modifier
                .fillMaxWidth()
        ){
            EndDateTextField { endDate = it }
            Icon(
                //falta implementar el calendario cliqueable
                imageVector = Icons.Filled.DateRange,
                contentDescription = "Select the deadline of this task",
                tint = Color.Blue,
                modifier = Modifier
                    .align(alignment = Alignment.CenterEnd)
                    .padding(end = 20.dp)
                    .size(30.dp)
                    .clickable {
                        //EndDateTextField { endDate = it } - @Composable invocations can only happen from the context of a @Composable function
                    }
            )
        }
    }
}

//https://proandroiddev.com/creating-a-form-using-jetpack-compose-and-material-design-6e18bc63b3d1

@Composable
fun EndDateTextField(endDate: (Long) -> Unit) {

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()

    var selectedDate by rememberSaveable { mutableStateOf("Deadline") }

    val context = LocalContext.current

    val calendar = Calendar.getInstance()
    val year: Int = calendar.get(Calendar.YEAR)
    val month: Int = calendar.get(Calendar.MONTH)
    val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val datePickerDialog =
        DatePickerDialog(context, { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val newDate = Calendar.getInstance()
            newDate.set(year, month, dayOfMonth)
            selectedDate = "${month.toMonthName()} $dayOfMonth, $year"
            endDate(newDate.timeInMillis)
        }, year, month, day)

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 63.dp),
        readOnly = true,
        value = selectedDate,
        onValueChange = {},
        trailingIcon = { Icons.Default.DateRange },
        interactionSource = interactionSource
    )

    if (isPressed) {
        datePickerDialog.show()
    }
}

fun Int.toMonthName(): String {
    return DateFormatSymbols().months[this]
}

fun Date.toFormattedString(): String {
    val simpleDateFormat = SimpleDateFormat("LLLL dd, yyyy", Locale.getDefault())
    return simpleDateFormat.format(this)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    AddTask()
}