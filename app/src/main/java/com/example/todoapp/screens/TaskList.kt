package com.example.todoapp.screens

data class List (
    val title: String,
    val description: String,
    val deadline: String,
    val type: String,
    var isChecked: Boolean = false
)

val listOfTasks = mutableListOf<List>()