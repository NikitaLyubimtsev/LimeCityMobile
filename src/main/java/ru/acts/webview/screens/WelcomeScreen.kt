package ru.acts.webview.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(navController: NavController) {

    // TODO Реализовать через ENUM
    val cities = mapOf(
        "Новомосковск" to "nmsk",
        "Тула" to "tula",
        "Елец" to "elets",
    )

    var selectedCity by remember { mutableStateOf("") }

    // val onCancel = "cancel"

    val onConfirmClick = {
        cities[selectedCity]?.let{
            navController.navigate("webview/${it}")
        } ?: throw Error("Error data of City") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text = "Выберите город"
                    )
                        },
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    //.fillMaxWidth()
            ) {
                Text(text = "Выберите город")
                Spacer(modifier = Modifier.height(8.dp))

                cities.keys.forEach {
                    Row(modifier = Modifier.padding(vertical = 4.dp)) {
                        RadioButton(
                            selected = selectedCity == it,
                            onClick = { selectedCity = it },
                           // modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(
                            text = it,
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .align(Alignment.CenterVertically)
                        )
                    }
                }
            }
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                //TODO авторизация
               // Button(
               //     onClick = onCancel
               // ) {
               //     Text(text = "Отмена")
               // }
                Button(
                    onClick = onConfirmClick,
                    enabled = selectedCity.isNotEmpty()
                ) {
                    Text(text = "Продолжить")
                }
            }
        }
    )


}