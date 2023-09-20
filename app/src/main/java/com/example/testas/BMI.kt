package com.example.testas

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Screen2(navController: NavHostController) {
    var heightInput by remember { mutableStateOf("") }
    var weightInput by remember { mutableStateOf("") }
    val weight = weightInput.toDoubleOrNull() ?: 0.0
    val heightInCm = heightInput.toDoubleOrNull() ?: 0.0

    val bmi = calculate(weight, heightInCm)

    // Round the BMI result to one decimal place
    val formattedBMI = "%.1f".format(bmi)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.bmi_calculator)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 56.dp) // Adjust this value as needed
            ) {
                Column(
                    modifier = Modifier.padding(40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.enter_your_information),
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .align(alignment = Alignment.Start)
                    )
                    EditNumberField(
                        label = R.string.height,
                        value = heightInput,
                        onValueChange = { heightInput = it },
                        modifier = Modifier
                            .padding(bottom = 32.dp)
                            .fillMaxWidth()
                    )
                    EditNumberField(
                        label = R.string.weight,
                        value = weightInput,
                        onValueChange = { weightInput = it },
                        modifier = Modifier
                            .padding(bottom = 32.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = stringResource(R.string.bmi_bmi, formattedBMI), // Display the calculated BMI here
                        style = MaterialTheme.typography.displaySmall
                    )
                    Spacer(modifier = Modifier.height(150.dp))
                }
            }
        }
    )
}

//BMI formula
private fun calculate(weight: Double, heightInCm: Double): Double {
    // Convert height from centimeters (cm) to meters (m)
    val heightInMeters = heightInCm / 100.0

    // BMI formula: BMI = weight (kg) / (height (m) * height (m))
    return weight / (heightInMeters * heightInMeters)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNumberField(
    @StringRes label: Int,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange, // Corrected parameter name
        singleLine = true,
        label = { Text(stringResource(label)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier
    )
}


