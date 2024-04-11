package com.example.todoapp.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todoapp.R
import com.example.todoapp.navigation.AppScreens
import com.example.todoapp.ui.theme.ToDoAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter") //Esta wea no se que hace pero me hice agregarlo para usar scaffold
@Composable
fun FirstScreen(navController: NavController){
    Scaffold {
        MainScreen(navController)
    }
}

@Composable
fun MainScreen (navController: NavController) {
    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        ) {
            Text(
                text = stringResource(R.string.title),
                fontSize = 22.sp,
                modifier = Modifier.align(Alignment.Center),
                fontWeight = FontWeight.Bold
            )
        }

        Box (
            modifier = Modifier
                .weight(1f)
        ){
            Image(
                painter = painterResource(id = R.drawable.main),
                contentDescription = stringResource(R.string.empty_list),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
            )
            Text(
                text = stringResource(R.string.no_tasks),
                fontSize = 17.sp,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 300.dp)
                    .width(255.dp),
                textAlign = TextAlign.Center
            )
        }

        Box (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            FloatingActionButton(
                onClick = {
                    navController.navigate(route = AppScreens.SecondScreen.route)
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 16.dp, bottom = 64.dp),
                containerColor = Color.Black,
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(R.string.add_task),
                )
            }
        }
    }
}
