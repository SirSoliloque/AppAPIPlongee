package com.example.apiexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                 mutableListOf<AdherentModel>()
            }
            val adherentChoosen = remember {
                mutableStateOf(AdherentModel());
            }
            val choix = remember {
                mutableStateOf(false)
            }
            val nouveau = remember {
                mutableStateOf(true)
            }
            Column {
                Row {
                    Button(modifier = Modifier.padding(pad) ,onClick = { Thread {
                        ApiClient().getAdherents(adherents)
                        choix.value = true
                        nouveau.value = false
                    }.start()}) {
                        Text(text = "Choisir un adherent")
                    }
                    Button(modifier = Modifier.padding(pad) ,onClick = {
                        adherentChoosen.value = AdherentModel()
                        choix.value = false
                        nouveau.value = true}) {
                        Text( text = "creer un adherent")
                    }
                }
                if(choix.value){
                    Surface {
                        LazyColumn(contentPadding = pad, ){
                            items(adherents.size, itemContent = { i ->
                                val it=adherents[i]
                                val adherent = remember {
                                    mutableStateOf(it)
                                }
                                Row (modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(1.dp)
                                    .background(Color.Cyan)){
                                    Button(onClick = { adherentChoosen.value = adherent.value
                                        choix.value = false
                                    }) {
                                        Text(text = adherent.value.id.toString().orEmpty()+" ")
                                        Text(text = adherent.value.nom.toString().orEmpty()+" ")
                                        Text(text = adherent.value.prenom.toString().orEmpty()+" ")
                                    }
                                }
                            })
                        }
                    }
                }
                else{
                    Surface(modifier = Modifier.padding(pad)) {
                        AdherentCard(adherent = adherentChoosen,nouveau.value)
                    }
                }
            }


        }
    )
}

@Composable
fun AdherentCard(adherent : MutableState<AdherentModel>,newAdherent : Boolean = false){
    Row {
        Column {
            TextField(
                modifier = Modifier.fillMaxWidth(1f/4),
                value = adherent.value.nom?.toString().orEmpty()    ,
                onValueChange = { change -> adherent.value= adherent.value.copy(nom = change)})
            TextField(
                modifier = Modifier.fillMaxWidth(1f/3),
                value = adherent.value.prenom?.toString().orEmpty(),
                onValueChange = { change -> adherent.value= adherent.value.copy(prenom = change) })
            TextField(
                modifier = Modifier.fillMaxWidth(1f/2),
                value = adherent.value.email?.toString().orEmpty(),
                onValueChange = { change ->adherent.value= adherent.value.copy(email = change)  })
            TextField(
                modifier = Modifier.fillMaxWidth(1f/4),
                value = adherent.value.forfait?.toString().orEmpty(),
                onValueChange = { change ->adherent.value= adherent.value.copy(forfait = change) })
            TextField(
                modifier = Modifier.fillMaxWidth(1f/3),
                value = adherent.value.dateCertificatMedical?.toString().orEmpty(),
                onValueChange = { change ->adherent.value= adherent.value.copy(dateCertificatMedical =   change) })
            TextField(
                modifier = Modifier.fillMaxWidth(1f/2),
                value = adherent.value.niveau?.toString().orEmpty(),
                onValueChange = { change -> adherent.value= adherent.value.copy(niveau = change.toInt()) })
        }
        if(newAdherent){
            Button(onClick = { Thread{ ApiClient().postAdherent(adherent.value)}.start() }) {
                Text(text = "creer")
            }
        }
        else{
            Button( onClick = { Thread{ ApiClient().putAdherent(adherent.value)}.start() }) {
                Text(text = "changer")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    APIExampleTheme {
        MainScreen()
    }
}