/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge


import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    Surface(color = MaterialTheme.colors.background) {
        val navController = rememberNavController()
        NavHost(navController, startDestination = "list_of_puppies") {
            composable("list_of_puppies") { ListOfPuppies(navController) }
            composable("details/{puppieId}") { id ->
                Details(navController, id.arguments?.getInt("puppieId")!!) }
        }
    }
}

@Composable
fun ListOfPuppies(navController: NavController) {

    val listOfPuppies = pupiesList
    Box() {
        Column() {
            LazyColumn(Modifier.fillMaxWidth()) {
                items(listOfPuppies) { item ->
                    PuppieListItem(
                        puppie = item,
                        modifier = Modifier.fillMaxWidth(),
                        navController

                    )
                }
            }
        }
    }

}

@Composable
fun Details(navController: NavController, puppieId: Int) {
    val listOfPuppies = pupiesList
    Column() {
        Text(text = "Hey: ${listOfPuppies[puppieId].name}")
        Text(text = "${listOfPuppies[puppieId].description}")
    }

}


@Composable
fun PuppieListItem(puppie: Puppie, modifier: Modifier, navController: NavController) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp)
            .clickable { navController.navigate("details/{${puppie.id}}") }) {

        val imageModifier = Modifier
            .size(80.dp)

            //.clip(shape = RoundedCornerShape(4.dp))


        Image(painter = painterResource(id = puppie.image),
            contentDescription = "",
            modifier = imageModifier,
            contentScale = ContentScale.Crop)

        Column(modifier = Modifier
            .fillMaxWidth(1f)
            .padding(8.dp)
            //.background(Color.Green)
        ) {
            Text(text = "${puppie.id.toString()} ${puppie.name}", fontFamily = FontFamily.SansSerif, style = MaterialTheme.typography.h5 )
            Row() {
                Text(text = puppie.breed, fontFamily = FontFamily.SansSerif, style = MaterialTheme.typography.subtitle2, modifier = Modifier.padding(8.dp))
                Text(text = puppie.sex, fontFamily = FontFamily.SansSerif, style = MaterialTheme.typography.subtitle2)
                Text(text = puppie.age.toString(), fontFamily = FontFamily.SansSerif, style = MaterialTheme.typography.subtitle2, modifier = Modifier.padding(8.dp))
            }

        }
    }
}



@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

//@Preview("Dark Theme", widthDp = 360, heightDp = 640)
//@Composable
//fun DarkPreview() {
//    MyTheme(darkTheme = true) {
//        MyApp()
//    }
//}


val puppiesImages = listOf(
    R.drawable.ash_goldsbrough_v0_mcllhy9m_unsplash,
    R.drawable.david_lezcano_m_doa_gtruw_unsplash,
    R.drawable.dustin_bowdige_xjxzj6c4jok_unsplash,
    R.drawable.james_barker_v3_zccwmjgm_unsplash,
    R.drawable.kieran_white_nkn25ufgfkq_unsplash
)

val pupiesList = listOf<Puppie>(
    Puppie(1, "Ash", "Armat", 5, "Male", puppiesImages[0], "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."),
    Puppie(2, "David", "Harrier", 6, "Male", puppiesImages[1], "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."),
    Puppie(3, "Dustin", "Armat", 10, "Female", puppiesImages[2], "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."),
    Puppie(4, "James", "Kokoni", 2, "Male", puppiesImages[3], "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."),
    Puppie(5, "Kieran", "Koolie", 11, "Female", puppiesImages[4], "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."),
    Puppie(6, "Ash", "Koolie", 2, "Male", puppiesImages[0], "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."),
    Puppie(7, "David", "Limer", 12, "Male", puppiesImages[1], "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."),
    Puppie(8, "Dustin", "Cur", 3, "Female", puppiesImages[2], "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."),
    Puppie(9, "James", "Kokoni", 3, "Male", puppiesImages[3], "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."),
    Puppie(10, "Kieran", "Whippet", 4, "Female", puppiesImages[4], "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
)

data class Puppie(
    val id: Int,
    val name: String,
    val breed: String,
    val age: Int,
    val sex: String,
    val image: Int,
    val description: String
)