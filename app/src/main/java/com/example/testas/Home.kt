package com.example.testas

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ComposeCardApp(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My First App") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                navigationIcon = {
                    if (navController.currentBackStackEntry?.destination?.route != "screen1") {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                        }
                    }
                },
            )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Info(
                    navController = navController, // Pass the navController here
                    title = stringResource(R.string.title),
                    name = stringResource(R.string.name),
                    imagePainter = painterResource(R.drawable.android_logo),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
                Contact(
                    email = stringResource(R.string.email),
                    phone = stringResource(R.string.phone),
                    social = stringResource(R.string.social),
                    imagePhone = painterResource(R.drawable.phone),
                    imageHub = painterResource(R.drawable.hub),
                    imageEmail = painterResource(R.drawable.mail),
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        })
}
@Composable
fun Info(
    navController: NavHostController,
    title: String,
    name: String,
    imagePainter: Painter,
    modifier: Modifier = Modifier,
) {
    var backgroundColor by remember { mutableStateOf(Color(0xFF000000)) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .padding(top = 60.dp)
                .clip(RoundedCornerShape(16.dp))
                .size(100.dp)
                .background(color = backgroundColor) // Use labeled argument to specify color
        ) {
            Image(
                painter = imagePainter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Text(
            text = name,
            fontSize = 40.sp,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp),
            textAlign = TextAlign.Justify
        )

        Text(
            text = title,
            modifier = Modifier.padding(16.dp),
            fontSize = 18.sp
        )

        Button(
            onClick = {
                // Update the background color when the button is clicked
                backgroundColor = Color((0xFF000000..0xFF999999).random())
            },
        ) {
            Text(text = stringResource(R.string.button), fontSize = 24.sp)
        }

        Button(
            modifier = Modifier.padding(top = 16.dp),
            onClick = {  navController.navigate("screen2") },
        ) {
            Text(text = stringResource(R.string.bmi), fontSize = 24.sp)
        }
        Button(
            modifier = Modifier.padding(16.dp),
            onClick = {  navController.navigate("screen3") },
        ) {
            Text(text = stringResource(R.string.scroll_list), fontSize = 24.sp)
        }

    }
}




@Composable
fun Contact(
    email: String,
    social: String,
    phone: String,
    imagePhone: Painter,
    imageEmail: Painter,
    imageHub: Painter,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 40.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = imagePhone,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(20.dp)
            )
            Text(
                text = phone,
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Justify
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = imageHub,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(20.dp)
            )
            Text(
                text = social,
                modifier = Modifier.padding(8.dp),
                fontSize = 18.sp
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = imageEmail,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(20.dp)
            )
            Text(
                text = email,
                modifier = Modifier.padding(8.dp),
                fontSize = 18.sp
            )
        }
    }
}