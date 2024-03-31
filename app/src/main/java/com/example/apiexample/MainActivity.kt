package com.example.apiexample

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apiexample.api.AdherentModel
import com.example.apiexample.api.ApiClient
import com.example.apiexample.ui.theme.APIExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            APIExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}



@Composable
fun MainScreen() {
    Scaffold(
        content = {pad->
            val id = remember {
                mutableStateOf(TextFieldValue())
            }

            val adherents = remember {
                mutableStateOf( mutableListOf<AdherentModel>())
            }
            if (adherents.value.isEmpty()) {

            }
            Button(modifier = Modifier.padding(pad) ,onClick = { Thread {
                Log.i("Main", "here")
                ApiClient().getAdherents(adherents)
                Log.i("Main", "there")
            }.start()}) {
                Text(text = "load")
            }
            Surface {
                LazyColumn(contentPadding = PaddingValues(0.dp) ){
                    items(adherents.value){
                        val adherent = remember {
                            mutableStateOf(it)
                        }
                        Row (modifier = Modifier
                            .fillMaxWidth()
                            .padding(1.dp)
                            .background(Color.Cyan)){
                            Column {
                                TextField(
                                    modifier = Modifier.fillMaxWidth(1f/4),
                                    value = adherent.value.nom,
                                    onValueChange = { change -> adherent.value.nom = change })
                                TextField(
                                    modifier = Modifier.fillMaxWidth(1f/3),
                                    value = adherent.value.prenom,
                                    onValueChange = { change -> adherent.value.prenom = change })
                                TextField(
                                    modifier = Modifier.fillMaxWidth(1f/2),
                                    value = adherent.value.email,
                                    onValueChange = { change -> adherent.value.email = change })
                                TextField(
                                    modifier = Modifier.fillMaxWidth(1f/4),
                                    value = adherent.value.forfait,
                                    onValueChange = { change -> adherent.value.forfait = change })
                                TextField(
                                    modifier = Modifier.fillMaxWidth(1f/3),
                                    value = adherent.value.dateCertificatMedical,
                                    onValueChange = { change -> adherent.value.dateCertificatMedical =   change })
                                TextField(
                                    modifier = Modifier.fillMaxWidth(1f/2),
                                    value = adherent.value.niveau.toString() ,
                                    onValueChange = { change -> adherent.value.niveau = change.toInt() })
                            }
                            Button( onClick = { ApiClient().putAdherent(adherent.value) }) {
                                Text(text = "change")
                            }
                        }
                    }
                }
            }

        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    APIExampleTheme {
        MainScreen()
    }
}