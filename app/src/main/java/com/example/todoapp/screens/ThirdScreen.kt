//3rd Screen
package com.example.todoapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todoapp.R
import com.example.todoapp.ui.theme.nunitoFontFamily
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ThirdScreen(
    navController: NavController,
    title: String, description: String,
    deadline: String, type: String,
    isChecked: Boolean
){
    Scaffold(
        topBar = {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.go_back),
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                )
                Text(
                    text = stringResource(R.string.task_details),
                    fontSize = 22.sp,
                    fontFamily = nunitoFontFamily,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    ) {
        val deadlineLong = deadline.toLongOrNull() ?: 0
        TaskDetails(title, description, deadlineLong, type, isChecked)
    }
}

@Composable
fun TaskDetails(
    title: String,
    description: String,
    deadline: Long,
    type: String,
    isChecked: Boolean
){
    val formattedDeadline = remember {
        SimpleDateFormat("LLLL dd, yyyy", Locale.getDefault())
    }.format(Date(deadline))

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 72.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontSize = 24.sp,
                fontFamily = nunitoFontFamily,
                fontWeight = FontWeight.Bold
            )
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = "Edit task",
                tint = Color.Blue,
                modifier = Modifier
                    .clickable {
                        //We go to the 2nd screen with the values that we have in this page and are able to edit them.
                    }
            )
        }

        val stateColor = if (isChecked) Color(0xFF78bc7a) else Color.Red
        val stateText = if (isChecked) "Completed" else "Incomplete"

        Text(
            modifier = Modifier
                .padding(end = 3.dp, top = 4.dp)
                .align(Alignment.End),
            text = stateText,
            fontSize = 14.sp,
            fontFamily = nunitoFontFamily,
            fontWeight = FontWeight.SemiBold,
            color = stateColor
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = description,
            fontSize = 16.sp,
            fontFamily = nunitoFontFamily,
            fontWeight = FontWeight.Normal,
            lineHeight = 24.sp,
            modifier = Modifier.width(328.dp)
        )

        Spacer(modifier = Modifier.height(13.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(id = R.drawable.clock),
                    contentDescription = stringResource(R.string.task_deadline),
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 8.dp)
                )
                Text(
                    text = "Before $formattedDeadline",
                    fontSize = 14.sp,
                    fontFamily = nunitoFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray
                )
            }

            val categoryColor = when (type){
                stringResource(R.string.priority_1) -> Color(0xFFff6c5c)
                stringResource(R.string.priority_2) -> Color(0xFFffd434)
                stringResource(R.string.priority_3) -> Color(0Xff687cec)
                stringResource(R.string.priority_4) -> Color(0xFF58cc54)
                else  -> Color.Gray
            }

            Surface(
                color = categoryColor,
                modifier = Modifier
                    .width(68.dp)
                    .height(24.dp),
                shape = CutCornerShape(topStart = 12.dp, bottomStart = 12.dp),

                ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = type,
                    fontSize = 12.sp,
                    color = Color.White,
                    fontFamily = nunitoFontFamily,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .padding(start = 8.dp, top = 4.dp)
                )
            }
        }
    }
}