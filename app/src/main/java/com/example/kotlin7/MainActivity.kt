package com.example.kotlin7

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.MainThread
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.window.layout.WindowMetricsCalculator
import com.example.kotlin7.ui.theme.Kotlin7Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Kotlin7Theme {
                ComputeWindowSizeClasses()
            }
        }
    }
}

@Composable
fun Activity.ComputeWindowSizeClasses() {
    val metrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(this)
    val width = metrics.bounds.width()
    val height = metrics.bounds.height()
    ImageWithDescription(width, height)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageWithDescription(width: Int, height: Int, modifier: Modifier = Modifier) {

    var screenNumber by remember { mutableIntStateOf(1) }

    val image = when(screenNumber){
        1 -> R.drawable.picture1
        2 -> R.drawable.picture2
        else -> R.drawable.picture3
    }

    val title = when(screenNumber){
        1 -> R.string.title_1
        2 -> R.string.title_2
        else -> R.string.title_3
    }

    val author = when(screenNumber){
        1 -> R.string.author_1
        2 -> R.string.author_2
        else -> R.string.author_3
    }

    val year = when(screenNumber){
        1 -> R.string.year_1
        2 -> R.string.year_2
        else -> R.string.year_3
    }

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Art Space",
                        fontWeight = FontWeight.Light
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color.LightGray
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            color = MaterialTheme.colorScheme.background
        ) {
            Column (
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .padding(top = 24.dp, start = 40.dp, end = 40.dp)
            ){
                Spacer(modifier = Modifier.height(30.dp))
                Image(
                    painter = painterResource(id = image),
                    contentDescription = null,
                    modifier = modifier
                        .widthIn(200.dp, 300.dp)
                        .background(color = Color.LightGray)
                        .shadow(elevation = 5.dp, spotColor = Color.Gray)
                        .padding(20.dp)
                )
                Spacer(modifier = Modifier.height(50.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = (width / 10).dp)
                        .background(color = Color.LightGray)
                ){
                    Text(
                        text = stringResource(id = title),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                    )
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, bottom = 16.dp)
                    ) {
                        Text(
                            text = stringResource(id = author),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(
                            text = stringResource(id = year),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Light
                        )
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                Row (
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Button(
                        onClick = {
                                  screenNumber = when (screenNumber){
                                      in 2..3 -> --screenNumber
                                      else -> 3
                                  }
                        },
                        modifier = modifier
                            .weight(1f)
                    ) {
                        Text(text = stringResource(R.string.previous))
                    }
                    Spacer(modifier = Modifier.width((width/5).dp))
                    Button(
                        onClick = {
                            screenNumber = when (screenNumber){
                            in 1..2 -> ++screenNumber
                            else -> 1
                            }
                                  },
                        modifier = modifier
                            .weight(1f)
                    ) {
                        Text(text = stringResource(R.string.next))
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun GreetingPreview() {
    Kotlin7Theme {
    }
}