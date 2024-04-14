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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ThirdScreen(){
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
                            //navController.popBackStack()
                        }
                )
                Text(
                    text = "Task Details",
                    fontSize = 22.sp
                )
            }
        }
    ) {
        TaskDetails()
    }
}

@Composable
fun TaskDetails(){
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
                text = "Buy a book", //Title
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = "Edit task", //onclick so we can edit it
                tint = Color.Blue
            )
        }

        Text(
            modifier = Modifier
                .padding(end = 3.dp, top = 4.dp)
                .align(Alignment.End),
            text = "Completed", //Task status - description
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF78bc7a) //Task status - color
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(   //Task description
            text = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna wirl\n" +
                    "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna wirl",
            fontSize = 16.sp,
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
                    contentDescription = "Task's Deadline",
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 8.dp)
                )
                Text(   //Deadline of the task
                    text = "Before 'deadline'",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray
                )
            }

            Surface(
                color = Color.Red,
                modifier = Modifier
                    .width(68.dp)
                    .height(24.dp),
                shape = CutCornerShape(topStart = 12.dp, bottomStart = 12.dp),

                ) {
                Text(   //Color according to the order of importance
                    textAlign = TextAlign.Center,
                    text = "Urgent",
                    fontSize = 12.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowPreview(){
    ThirdScreen()
}